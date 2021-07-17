package com.fabrick.rs.controller;

import com.fabrick.rs.api.FabrickApi;
import com.fabrick.rs.api.exception.*;
import com.fabrick.rs.dto.FabrickBalanceResponse;
import com.fabrick.rs.dto.FabrickTransactionsDto;
import com.fabrick.rs.dto.TransferDto;
import com.fabrick.bl.service.IFabrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class FabricApiController implements FabrickApi {

    @Autowired
    private IFabrickService fabrickService;

    @Override
    public ResponseEntity<FabrickBalanceResponse> readBalance(String accountId) throws FabrickException {

        FabrickBalanceResponse fabrickBalanceResponse = fabrickService.getBalance(accountId);

        return new ResponseEntity<>(fabrickBalanceResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FabrickTransactionsDto> readTransactions(String accountId,
                                                   String fromAccountingDate,
                                                   String toAccountingDate) throws FabrickNotFoundException, FabrickConnectionException, FabrickInputException {

        FabrickTransactionsDto result = fabrickService.getTransactions(accountId, fromAccountingDate, toAccountingDate);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> makeTransfer(TransferDto transferDto) throws FabrickApplicationException {
        String resp = fabrickService.makeTransfer(transferDto);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
