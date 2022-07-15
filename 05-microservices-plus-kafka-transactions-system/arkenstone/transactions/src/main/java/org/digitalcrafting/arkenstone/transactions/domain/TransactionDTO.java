package org.digitalcrafting.arkenstone.transactions.domain;

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
public class TransactionDTO {
    private Long id;
    private String accountNumber;
    private String foreignAccountNumber;
    private String description;
    private TransactionTypeEnum type;
    private CurrencyEnum currency;
    private TransactionStatusEnum status;
    private BigDecimal amount;
    private Date date;
}
