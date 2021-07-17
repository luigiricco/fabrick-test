package com.fabrick.rs;

import com.fabrick.bl.service.IFabrickService;
import com.fabrick.rs.api.FabrickApi;
import com.fabrick.rs.api.exception.FabrickApplicationException;
import com.fabrick.rs.api.exception.GlobalExceptionHandler;
import com.fabrick.rs.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private FabrickApi fabrickApi;

    @MockBean
    private IFabrickService fabrickService;

    @Autowired
    protected WebApplicationContext wac;

    @BeforeEach
    public void setUp() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        globalExceptionHandler.setErrorResponseProvider(globalExceptionHandler.errorResponseProvider());
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getBalance() throws Exception {
        FabrickBalanceResponse resp = new FabrickBalanceResponse();
        resp.setPayload(new FabrickBalanceDto());
        resp.getPayload().setBalance("123");
        resp.getPayload().setAvailableBalance("234");
        resp.getPayload().setCurrency("EUR");
        resp.getPayload().setDate("2021-07-21");

        Mockito.when(fabrickService.getBalance("123")).thenReturn(resp);

        MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders.get("/balance/123");

        String response = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertNotNull(response);
    }

    @Test
    public void getTransfactions() throws Exception {
        FabrickTransactionsDto resp = new FabrickTransactionsDto();
        resp.addList(FabrickTransactionDto.builder().transactionId("123").build());

        Mockito.when(this.fabrickService.getTransactions("123", "2021-01-01", "2021-12-31")).thenReturn(resp);

        MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders.
                get("/transactions/123")
                .param("fromAccountingDate", "2021-01-01")
                .param("toAccountingDate","2021-12-31");

        String response = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertNotNull(response);
    }

    @Test
    public void postTransfer() throws Exception {
        TransferDto transferDto = new TransferDto();
        transferDto.setCurrency("EUR");
        transferDto.setAccountId(123L);
        transferDto.setAmount("123");
        transferDto.setExecutionDate("2021-07-21");
        transferDto.setDescription("DESC");
        transferDto.setReceiverName("PIPPO");

        Mockito.when(this.fabrickService.makeTransfer(transferDto)).thenThrow(new FabrickApplicationException("API000", "Errore tecnico - La condizione BP049 non e' prevista per il conto id " + transferDto.getAccountId()));

        MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders
                .post("/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(transferDto));
        String response = mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertNotNull(response);
    }
}
