package com.fabrick.rs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class FabrickTransactionsDto {
    @JsonProperty("list")
    private List<FabrickTransactionDto> list = new ArrayList<>();

    public void addList(FabrickTransactionDto fabrickTransactionsDto) {
        this.list.add(fabrickTransactionsDto);
    }
}
