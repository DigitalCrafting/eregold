package org.digitalcrafting.eregold.api.accounts;

import lombok.Data;
import org.digitalcrafting.eregold.domain.accounts.AccountTypeEnum;

@Data
public class CreateAccountRequest {
    private AccountTypeEnum accountType;
}
