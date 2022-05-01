package org.digitalcrafting.arkenstone.domain.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.digitalcrafting.arkenstone.domain.transactions.TransactionHistoryDTO;

import java.math.BigDecimal;
import java.util.List;

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
    private List<TransactionHistoryDTO> transactionsList;
}
