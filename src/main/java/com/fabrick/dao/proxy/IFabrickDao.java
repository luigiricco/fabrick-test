package com.fabrick.dao.proxy;

import com.fabrick.rs.api.exception.FabrickApplicationException;
import com.fabrick.rs.api.exception.FabrickConnectionException;
import com.fabrick.rs.api.exception.FabrickException;
import com.fabrick.rs.api.exception.FabrickNotFoundException;
import com.fabrick.rs.dto.FabrickBalanceResponse;
import com.fabrick.rs.dto.FabrickTransactionsDto;
import com.fabrick.rs.dto.TransferDto;

import java.net.URISyntaxException;

public interface IFabrickDao {
    FabrickBalanceResponse getBalance(String accountId) throws FabrickException;
    FabrickTransactionsDto getTransactions(String accountId, String fromAccountingDate, String toAccountingDate) throws URISyntaxException, FabrickConnectionException, FabrickNotFoundException;
    String makeTransfer(TransferDto transferDto) throws FabrickApplicationException;
}
