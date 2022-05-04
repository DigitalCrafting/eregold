package org.digitalcrafting.eregold.repository.clients.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String accountNumber;
    private String accountName;
    private BigDecimal currentBalance;
    private String currency;
    private String type;
}
