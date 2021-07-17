package com.fabrick.rs.api.exception;


import com.fabrick.rs.dto.ErrorDetails;

import java.util.List;

@FunctionalInterface
public interface ErrorResponseProvider {
    Object provideResponse(String code, String message);
}
