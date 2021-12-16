package org.digitalcrafting.eregold.api.accounts;

import lombok.Data;
import org.digitalcrafting.eregold.domain.accounts.AccountTypeEnum;
import org.digitalcrafting.eregold.domain.accounts.CurrencyEnum;

@Data
public class CreateAccountRequest {
    private AccountTypeEnum accountType;
    private CurrencyEnum currency;
}
