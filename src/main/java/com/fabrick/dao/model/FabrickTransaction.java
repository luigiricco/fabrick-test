package com.fabrick.dao.model;

import com.fabrick.rs.enumeration.FabrickTransactionTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table( name = "FBK_TRANSACTION",
        indexes = @Index(   name = "FBK_IDX_TRANSACTION_VALUE_DATE",
                            columnList = "valueDate"))
public class FabrickTransaction {

    @Id
    @GeneratedValue(generator = "FBK_TRANSACTION_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "FBK_TRANSACTION_SEQ", sequenceName = "FBK_TRANSACTION_SEQ")
    private Long id;
    private String transactionId;
    private String operationId;
    private Date accountingDate;
    private Date valueDate;
    private double amount;
    private String currency;
    private String description;
    private FabrickTransactionTypeEnum type;
}
