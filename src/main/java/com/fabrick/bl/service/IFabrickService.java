package com.fabrick.bl.service;

import com.fabrick.rs.api.exception.*;
import com.fabrick.rs.dto.FabrickBalanceResponse;
import com.fabrick.rs.dto.FabrickTransactionsDto;
import com.fabrick.rs.dto.TransferDto;

public interface IFabrickService {
    FabrickBalanceResponse getBalance(String accountId) throws FabrickException;
    FabrickTransactionsDto getTransactions(String accountId, String fromAccountingDate, String toAccountingDate) throws FabrickConnectionException, FabrickNotFoundException, FabrickInputException;
    String makeTransfer(TransferDto transferDto) throws FabrickApplicationException;
}
