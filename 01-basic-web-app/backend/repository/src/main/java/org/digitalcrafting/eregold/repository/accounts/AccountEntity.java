package org.digitalcrafting.eregold.repository.accounts;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountEntity {
    private String accountNumber;
    private BigDecimal currentBalance;
    private String currency;
    private String type;
}
