package org.digitalcrafting.arkenstone.domain.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.digitalcrafting.arkenstone.domain.accounts.CurrencyEnum;

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
    private BigDecimal amount;
    private Date date;
}
