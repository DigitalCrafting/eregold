package org.digitalcrafting.eregold.repository.transactions;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionEntity {
    private Long id;
    private String srcAccount;
    private String dstAccount;
    private String description;
    private String type;
    private String currency;
    private BigDecimal amount;
    private Date date;
}
