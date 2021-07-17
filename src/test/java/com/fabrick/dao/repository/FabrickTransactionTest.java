package com.fabrick.dao.repository;

import com.fabrick.dao.model.FabrickTransaction;
import com.fabrick.rs.enumeration.FabrickTransactionTypeEnum;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;

@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FabrickTransactionTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FabrickTransactionRepository fabrickTransactionRepository;

    @Test
    public void test01() {
        FabrickTransaction fabrickTransaction = new FabrickTransaction();
        fabrickTransaction.setAmount(-800D);
        fabrickTransaction.setCurrency("EUR");
        fabrickTransaction.setDescription("description");
        fabrickTransaction.setAccountingDate(new Date());
        fabrickTransaction.setValueDate(new Date());
        fabrickTransaction.setType(FabrickTransactionTypeEnum.GBS_ACCOUNT_TRANSACTION_TYPE_0010);
        fabrickTransaction.setOperationId("OPER");

        this.fabrickTransactionRepository.save(fabrickTransaction);

        List<FabrickTransaction> all = this.fabrickTransactionRepository.findAll();

        Assertions.assertNotNull(all);
        Assertions.assertEquals(1, all.size());
        Assertions.assertNotNull(all.get(0).getId());
        Assertions.assertEquals(-800, all.get(0).getAmount());
    }

    @Test
    public void test02() {
        FabrickTransaction fabrickTransaction = new FabrickTransaction();
        fabrickTransaction.setTransactionId("1331714087");
        fabrickTransaction.setAmount(-800D);
        fabrickTransaction.setCurrency("EUR");
        fabrickTransaction.setDescription("BA JOHN DOE PAYMENT INVOICE 75/2017");
        fabrickTransaction.setAccountingDate(new Date());
        fabrickTransaction.setValueDate(new Date());
        fabrickTransaction.setType(FabrickTransactionTypeEnum.GBS_ACCOUNT_TRANSACTION_TYPE_0034);
        fabrickTransaction.setOperationId("00000000273015");

        this.fabrickTransactionRepository.save(fabrickTransaction);

        List<FabrickTransaction> all = this.fabrickTransactionRepository.findAll();

        Assertions.assertNotNull(all);
        Assertions.assertEquals(1, all.size());
        Assertions.assertNotNull(all.get(0).getId());
        Assertions.assertEquals(-800, all.get(0).getAmount());
    }
}
