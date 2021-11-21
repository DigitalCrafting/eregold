package org.digitalcrafting.eregold.domain.accounts;

import lombok.Data;

@Data
public class AccountModel {
    private String accountNumber;
    private Double currentBalance;
    private String currency;
    private String type;
}
