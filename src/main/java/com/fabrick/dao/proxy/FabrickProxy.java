package com.fabrick.dao.proxy;

import com.fabrick.config.FabrickProperties;
import com.fabrick.rs.api.exception.FabrickApplicationException;
import com.fabrick.rs.api.exception.FabrickConnectionException;
import com.fabrick.rs.api.exception.FabrickException;
import com.fabrick.rs.api.exception.FabrickNotFoundException;
import com.fabrick.rs.dto.FabrickBalanceResponse;
import com.fabrick.rs.dto.FabrickTransactionsDto;
import com.fabrick.rs.dto.FabrickTransactionsResponse;
import com.fabrick.rs.dto.TransferDto;
import com.fabrick.rs.enumeration.FabrickTransactionTypeEnum;
import com.fabrick.rs.enumeration.FabrickTransactionTypeEnumDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Slf4j
public class FabrickProxy implements IFabrickDao {
    @Autowired
    private FabrickProperties fabrickProperties;

    public FabrickBalanceResponse getBalance(String accountId) throws FabrickException {
        String fullUrl = fabrickProperties.getBaseUrl() + fabrickProperties.getBalancePath();

        fullUrl = fullUrl.replace("{accountId}", accountId);

        HttpGet get = new HttpGet(fullUrl);

        fillHttpRequestBase(get);
        FabrickBalanceResponse resp = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse response = client.execute(get);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //la risposta è ok
                String body = EntityUtils.toString(response.getEntity());

                resp = new ObjectMapper().readValue(body, FabrickBalanceResponse.class);
            }
        } catch (IOException e) {
            log.warn("Errore nel reperimento balance: " + e.getMessage(), e);
            throw new FabrickConnectionException(e.getMessage());
        }

        if (resp == null) throw new FabrickNotFoundException("Balance Not Found");

        return resp;
    }

    public FabrickTransactionsDto getTransactions(String accountId, String fromAccountingDate, String toAccountingDate) throws URISyntaxException, FabrickConnectionException, FabrickNotFoundException {
        String fullUrl = fabrickProperties.getBaseUrl() + fabrickProperties.getTransactionsPath();

        fullUrl = fullUrl.replace("{accountId}", accountId);

        URIBuilder builder = new URIBuilder(fullUrl);
        builder.addParameter("fromAccountingDate", fromAccountingDate);
        builder.addParameter("toAccountingDate", toAccountingDate);

        HttpGet get = new HttpGet(builder.build());

        fillHttpRequestBase(get);

        FabrickTransactionsResponse resp = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse response = client.execute(get);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //la risposta è ok
                String body = EntityUtils.toString(response.getEntity());
                log.info("Body: " + body);

                ObjectMapper mapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addDeserializer(FabrickTransactionTypeEnum.class, new FabrickTransactionTypeEnumDeserializer());
                mapper.registerModule(module);

                resp = mapper.readValue(body, FabrickTransactionsResponse.class);
            }
        } catch (IOException e) {
            log.warn("Errore nel reperimento transactions: " + e.getMessage(), e);
            throw new FabrickConnectionException(e.getMessage());
        }

        if (resp == null || resp.getPayload() == null) throw new FabrickNotFoundException("Transactions Not Found");

        return resp.getPayload();
    }

    public String makeTransfer(TransferDto transferDto) throws FabrickApplicationException {

        FabrickApplicationException fabrickApplicationException = new FabrickApplicationException("API000", "Errore tecnico - La condizione BP049 non e' prevista per il conto id " + transferDto.getAccountId());

        throw fabrickApplicationException;
    }


    private void buildClient() {
        CloseableHttpClient client =HttpClients.createDefault();
    }

    private void fillHttpRequestBase(HttpRequestBase httpRequest) {
        httpRequest.addHeader("Auth-Schema", fabrickProperties.getAuthSchema());
        httpRequest.addHeader("Api-Key",  fabrickProperties.getApiKey());
    }
}
