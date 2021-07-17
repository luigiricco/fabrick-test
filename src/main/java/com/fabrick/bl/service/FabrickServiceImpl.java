package com.fabrick.bl.service;

import com.fabrick.dao.model.FabrickTransaction;
import com.fabrick.dao.model.modelmapper.TransactionConverter;
import com.fabrick.dao.proxy.IFabrickDao;
import com.fabrick.dao.repository.FabrickTransactionRepository;
import com.fabrick.rs.api.exception.*;
import com.fabrick.rs.dto.FabrickBalanceResponse;
import com.fabrick.rs.dto.FabrickTransactionsDto;
import com.fabrick.rs.dto.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class FabrickServiceImpl implements IFabrickService{

    @Value("${date-pattern:yyyy-MM-dd}")
    private String datePattern = "yyyy-MM-dd";

    @Autowired
    private IFabrickDao fabrickProxy;

    @Autowired
    private FabrickTransactionRepository fabrickTransactionRepository;

    @Autowired
    private TransactionConverter transactionConverter;


    @Override
    public FabrickBalanceResponse getBalance(String accountId) throws FabrickException {
        if (accountId == null) {
            throw new FabrickInputException("accountId is null");
        }

        return fabrickProxy.getBalance(accountId);
    }

    @Override
    public FabrickTransactionsDto getTransactions(String accountId, String fromAccountingDate, String toAccountingDate) throws FabrickConnectionException, FabrickNotFoundException, FabrickInputException {
        if (accountId == null) {
            throw new FabrickInputException("accountId is null");
        }

        if (fromAccountingDate == null) {
            throw new FabrickInputException("fromAccountingDate is null");
        }

        if (toAccountingDate == null) {
            throw new FabrickInputException("toAccountingDate is null");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        sdf.setLenient(false);

        try {
            sdf.parse(fromAccountingDate);
            sdf.parse(toAccountingDate);
        } catch (ParseException e) {
            throw new FabrickInputException("parsing dates are incorrects");
        }

        FabrickTransactionsDto fabrickTransactionsDto;

        try {
             fabrickTransactionsDto = fabrickProxy.getTransactions(accountId, fromAccountingDate, toAccountingDate);
        } catch (URISyntaxException e) {
            throw new FabrickInputException("URL is incorrect");
        }

        if (fabrickTransactionsDto != null) {
            List<FabrickTransaction> listTransactions = transactionConverter.convert(fabrickTransactionsDto.getList());
            fabrickTransactionRepository.saveAll(listTransactions);
        }

        return fabrickTransactionsDto;
    }

    @Override
    public String makeTransfer(TransferDto transferDto) throws FabrickApplicationException {
        return fabrickProxy.makeTransfer(transferDto);
    }
}
