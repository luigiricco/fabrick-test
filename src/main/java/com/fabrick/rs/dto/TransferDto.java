package com.fabrick.rs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString
@Setter
@Getter
@Validated
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TransferDto {
    @JsonProperty(required = true)
    @NotNull
    public Long accountId;
    @JsonProperty(required = true)
    @NotEmpty
    public String receiverName;
    @JsonProperty(required = true)
    @NotEmpty
    public String description;
    @JsonProperty(required = true)
    @NotEmpty
    public String currency;
    @JsonProperty(required = true)
    @NotEmpty
    public String amount;
    @JsonProperty(required = true)
    @NotEmpty
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
    public String executionDate;
}
