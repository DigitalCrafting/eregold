package org.digitalcrafting.arkenstone.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsDTO {
    private String accountNumber;
    private String accountName;
    private BigDecimal currentBalance;
    private CurrencyEnum currency;
    private AccountTypeEnum type;
}
