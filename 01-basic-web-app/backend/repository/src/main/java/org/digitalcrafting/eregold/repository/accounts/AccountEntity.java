package org.digitalcrafting.eregold.repository.accounts;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountEntity {
    private String accountNumber;
    private BigDecimal currentBalance;
    private String currency;
    private String type;
}
