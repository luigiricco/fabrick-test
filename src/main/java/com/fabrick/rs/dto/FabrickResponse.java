package com.fabrick.rs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FabrickResponse {
    @JsonProperty("status")
    protected String status;
    @JsonProperty("error")
    protected List<String> error;
}
