package org.digitalcrafting.eregold.domain.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.digitalcrafting.eregold.domain.transactions.TransactionModel;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsModel {
    private String accountNumber;
    private String accountName;
    private BigDecimal currentBalance;
    private CurrencyEnum currency;
    private AccountTypeEnum type;
    private List<TransactionModel> transactionsList;
}
