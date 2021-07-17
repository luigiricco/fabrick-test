package com.fabrick.rs.api;

import com.fabrick.rs.api.exception.*;
import com.fabrick.rs.dto.FabrickBalanceResponse;
import com.fabrick.rs.dto.FabrickTransactionsDto;
import com.fabrick.rs.dto.TransferDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@Api(value = "/account")
public interface FabrickApi {

    @GetMapping(value = "/alive",
                produces = { MediaType.TEXT_PLAIN_VALUE })
    default ResponseEntity<String> alive() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/balance/{accountId}",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<FabrickBalanceResponse> readBalance(@ApiParam(value = "The account ID", required = true) @PathVariable("accountId") String accountId) throws FabrickException {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(value = "/transactions/{accountId}",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    default ResponseEntity<FabrickTransactionsDto> readTransactions(@ApiParam(value = "The account ID" ,required=true ) @PathVariable(value = "accountId", required = true) String accountId,
                                                                    @ApiParam(value = "The from accounting Date" ,required=true ) @RequestParam(value = "fromAccountingDate", required = true) String fromAccountingDate,
                                                                    @ApiParam(value = "The to accounting Date" ,required=true ) @RequestParam(value = "toAccountingDate", required = true) String toAccountingDate) throws FabrickNotFoundException, FabrickConnectionException, FabrickInputException {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(value = "/transfer",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    default ResponseEntity<String> makeTransfer(@ApiParam(value = "The transfer data" ,required=true )
                                                    @Valid @RequestBody TransferDto transferDto) throws FabrickApplicationException {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
