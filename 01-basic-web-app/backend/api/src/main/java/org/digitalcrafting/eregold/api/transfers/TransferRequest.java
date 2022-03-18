package org.digitalcrafting.eregold.api.transfers;

import lombok.Data;
import org.digitalcrafting.eregold.domain.accounts.CurrencyEnum;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String srcAccount;
    private String dstAccount;
    private String description;
    private BigDecimal amount;
    private CurrencyEnum currency;
}
