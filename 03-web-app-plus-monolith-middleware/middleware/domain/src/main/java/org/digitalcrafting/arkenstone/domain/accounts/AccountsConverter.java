package org.digitalcrafting.arkenstone.domain.accounts;

import org.digitalcrafting.arkenstone.repository.accounts.AccountEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class AccountsConverter {
    private AccountsConverter() {}

    public static List<AccountDTO> toDTOList(List<AccountEntity> entityList) {
        List<AccountDTO> accountDTOList = null;

        if (entityList != null && !entityList.isEmpty()) {
            accountDTOList = entityList.stream()
                    .map(AccountsConverter::toDTO)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return accountDTOList;
    }

    public static AccountDTO toDTO(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        return AccountDTO.builder()
                .accountNumber(entity.getAccountNumber())
                .accountName(entity.getAccountName())
                .currency(CurrencyEnum.valueOf(entity.getCurrency()))
                .type(AccountTypeEnum.valueOf(entity.getType()))
                .currentBalance(entity.getCurrentBalance())
                .build();
    }

    public static AccountDetailsDTO toDetailsDTO(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        return AccountDetailsDTO.builder()
                .accountNumber(entity.getAccountNumber())
                .accountName(entity.getAccountName())
                .currentBalance(entity.getCurrentBalance())
                .currency(CurrencyEnum.valueOf(entity.getCurrency()))
                .type(AccountTypeEnum.valueOf(entity.getType()))
                .build();
    }
}
