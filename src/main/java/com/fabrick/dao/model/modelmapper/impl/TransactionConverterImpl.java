package com.fabrick.dao.model.modelmapper.impl;

import com.fabrick.dao.model.FabrickTransaction;
import com.fabrick.dao.model.modelmapper.TransactionConverter;
import com.fabrick.rs.dto.FabrickTransactionDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
@Component
public class TransactionConverterImpl implements TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FabrickTransaction convert(FabrickTransactionDto transactionDto) {
        return modelMapper.map(transactionDto, FabrickTransaction.class);
    }

    @Override
    public List<FabrickTransaction> convert(List<FabrickTransactionDto> list) {
        return list.stream().map(this::convert).collect(Collectors.toList());
    }
}
