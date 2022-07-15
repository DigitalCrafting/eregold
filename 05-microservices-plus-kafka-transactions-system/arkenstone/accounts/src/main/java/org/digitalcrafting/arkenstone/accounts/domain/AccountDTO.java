package org.digitalcrafting.arkenstone.accounts.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDTO {
    private String accountNumber;
    private String accountName;
    private BigDecimal currentBalance;
    private BigDecimal availableBalance;
    private CurrencyEnum currency;
    private AccountTypeEnum type;
}
