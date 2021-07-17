package com.fabrick.rs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FabrickBalanceResponse extends FabrickResponse {
    @JsonProperty
    private FabrickBalanceDto payload;

}
