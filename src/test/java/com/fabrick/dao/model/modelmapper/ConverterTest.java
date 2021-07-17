package com.fabrick.dao.model.modelmapper;

import com.fabrick.dao.model.FabrickTransaction;
import com.fabrick.dao.model.modelmapper.config.ModelMapperConfig;
import com.fabrick.dao.model.modelmapper.impl.TransactionConverterImpl;
import com.fabrick.rs.dto.FabrickTransactionDto;
import com.fabrick.rs.enumeration.FabrickTransactionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes= {ModelMapperConfig.class, TransactionConverterImpl.class}, loader= AnnotationConfigContextLoader.class)
public class ConverterTest {
    @Autowired
    private TransactionConverter transactionConverter;

    @Test
    public void test01()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date compareDate = null;
        try {
            compareDate = sdf.parse("2019-04-01");

            FabrickTransactionDto transactionDto = FabrickTransactionDto.builder()
                    .transactionId("1331714087")
                    .operationId("00000000273015")
                    .accountingDate("2019-04-01")
                    .valueDate("2019-04-01")
                    .type(FabrickTransactionTypeEnum.GBS_ACCOUNT_TRANSACTION_TYPE_0010)
                    .amount(-800D)
                    .currency("EUR")
                    .description("BA JOHN DOE PAYMENT INVOICE 75/2017")
                    .build();
            FabrickTransaction fabrickTransaction = transactionConverter.convert(transactionDto);

            Assertions.assertNotNull(fabrickTransaction);
            Assertions.assertEquals(transactionDto.getTransactionId(), fabrickTransaction.getTransactionId());
            Assertions.assertEquals(transactionDto.getOperationId(), fabrickTransaction.getOperationId());
            Assertions.assertEquals(transactionDto.getAmount(), fabrickTransaction.getAmount());
            Assertions.assertEquals(transactionDto.getDescription(), fabrickTransaction.getDescription());
            Assertions.assertEquals(transactionDto.getCurrency(), fabrickTransaction.getCurrency());
            Assertions.assertEquals(transactionDto.getType(), fabrickTransaction.getType());
            Assertions.assertEquals(compareDate, fabrickTransaction.getAccountingDate());
            Assertions.assertEquals(compareDate, fabrickTransaction.getValueDate());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void test2() {
        FabrickTransactionDto transactionDto = FabrickTransactionDto.builder()
                .transactionId("1331714087")
                .operationId("00000000273015")
                .accountingDate("2019-04-01")
                .valueDate("2019-04-01")
                .type(FabrickTransactionTypeEnum.GBS_ACCOUNT_TRANSACTION_TYPE_0010)
                .amount(-800D)
                .currency("EUR")
                .description("BA JOHN DOE PAYMENT INVOICE 75/2017")
                .build();

        List<FabrickTransactionDto> all = new ArrayList<>();
        all.add(transactionDto);

        List<FabrickTransaction> allConverted = this.transactionConverter.convert(all);

        Assertions.assertNotNull(allConverted);
        Assertions.assertEquals(1, allConverted.size());
    }
}
