package org.digitalcrafting.arkenstone.accounts.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    private String accountNumber;
    private String accountName;
    private BigDecimal currentBalance;
    private BigDecimal availableBalance;
    private String currency;
    private String type;
}
