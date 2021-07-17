package com.fabrick.rs.dto;

import com.fabrick.rs.enumeration.FabrickTransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FabrickTransactionDto {
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("operationId")
    private String operationId;
    @JsonProperty("accountingDate")
    private String accountingDate;
    @JsonProperty("valueDate")
    private String valueDate;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private FabrickTransactionTypeEnum type;
}
