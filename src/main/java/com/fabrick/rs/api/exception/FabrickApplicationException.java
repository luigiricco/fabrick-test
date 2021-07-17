package com.fabrick.rs.api.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FabrickApplicationException extends FabrickException {
    private String code;

    public FabrickApplicationException(String code, String messaage) {
        super(messaage);
        this.code = code;
    }
}
