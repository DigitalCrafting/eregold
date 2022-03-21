package org.digitalcrafting.eregold.api.accounts;

import org.digitalcrafting.eregold.domain.accounts.AccountModel;
import org.digitalcrafting.eregold.domain.accounts.AccountTypeEnum;
import org.digitalcrafting.eregold.domain.accounts.CurrencyEnum;
import org.digitalcrafting.eregold.repository.accounts.AccountEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class AccountsConverter {
    public static List<AccountModel> convert(List<AccountEntity> entityList) {
        List<AccountModel> accountModelList = null;

        if (entityList != null && !entityList.isEmpty()) {
            accountModelList = entityList.stream()
                    .map(AccountsConverter::convert)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return accountModelList;
    }

    public static AccountModel convert(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        return AccountModel.builder()
                .accountNumber(entity.getAccountNumber())
                .currency(CurrencyEnum.valueOf(entity.getCurrency()))
                .type(AccountTypeEnum.valueOf(entity.getType()))
                .currentBalance(entity.getCurrentBalance())
                .build();
    }
}
