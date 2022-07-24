package org.digitalcrafting.arkenstone.transactions.repository.clients.accounts;

import lombok.Builder;
import lombok.Data;
import org.digitalcrafting.arkenstone.transactions.domain.CurrencyEnum;

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
