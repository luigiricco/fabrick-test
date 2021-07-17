package com.fabrick.rs.api.exception;

import com.fabrick.rs.dto.Error;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    @Setter
    private ErrorResponseProvider errorResponseProvider;

    @Bean
    @ConditionalOnMissingBean
    public ErrorResponseProvider errorResponseProvider() {
        return (faultCode, message) -> Error.builder().code(faultCode).error(message).build();
    }

    @ExceptionHandler(FabrickNotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(Exception ex, WebRequest request) {
        log.error("Not found error", ex);
        Object response = errorResponseProvider.provideResponse(ErrorCodes.RESOURCE_NOT_FOUND, "Resource Not Found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FabrickConnectionException.class)
    public ResponseEntity<Object> notConnectionExceptionHandler(Exception ex, WebRequest request) {
        log.error("Fabrick Connection error", ex);
        Object response = errorResponseProvider.provideResponse(ErrorCodes.CONNECTION_NOT_FOUND, "Connection Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FabrickInputException.class)
    public ResponseEntity<Object> notInputExceptionHandler(Exception ex, WebRequest request) {
        log.error("Input error", ex);
        Object response = errorResponseProvider.provideResponse(ErrorCodes.INPUT_NOT_FOUND, "Input parameter not compliant or not found: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(FabrickApplicationException.class)
    public ResponseEntity<Object> applicationExceptionHandler(Exception ex, WebRequest request) {
        log.error("Input error", ex);
        FabrickApplicationException fabrickApplicationException = (FabrickApplicationException)ex;
        Object response = errorResponseProvider.provideResponse(fabrickApplicationException.getCode(), fabrickApplicationException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
