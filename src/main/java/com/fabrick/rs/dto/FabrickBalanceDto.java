package com.fabrick.rs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FabrickBalanceDto {
    @JsonProperty
    private String date;
    @JsonProperty
    private String balance;
    @JsonProperty
    private String availableBalance;
    @JsonProperty
    private String currency;
}
