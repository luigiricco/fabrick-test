package com.fabrick.rs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FabrickTransactionsResponse extends FabrickResponse {
    @JsonProperty("payload")
    private FabrickTransactionsDto payload;

}
