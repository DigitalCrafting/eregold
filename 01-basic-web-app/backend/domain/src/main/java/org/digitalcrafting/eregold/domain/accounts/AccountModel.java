package org.digitalcrafting.eregold.domain.accounts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountModel {
    private String accountNumber;
    private Double currentBalance;
    private String currency;
    private String type;
}
