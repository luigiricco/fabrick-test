package com.fabrick.dao.model.modelmapper;

import com.fabrick.dao.model.FabrickTransaction;
import com.fabrick.rs.dto.FabrickTransactionDto;

import java.util.List;

public interface TransactionConverter {
    public FabrickTransaction convert(FabrickTransactionDto transactionDto);
    public List<FabrickTransaction> convert(List<FabrickTransactionDto> list);
}
