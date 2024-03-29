package org.digitalcrafting.arkenstone.transactions.repository.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    private Long id;
    private String accountNumber;
    private String foreignAccountNumber;
    private String description;
    private String type;
    private String status;
    private String currency;
    private BigDecimal amount;
    private Date date;
}
