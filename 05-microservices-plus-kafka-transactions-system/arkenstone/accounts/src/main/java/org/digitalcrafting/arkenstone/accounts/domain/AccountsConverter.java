package org.digitalcrafting.arkenstone.accounts.domain;

import org.digitalcrafting.arkenstone.accounts.repository.AccountEntity;

import java.math.BigDecimal;
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
                .availableBalance(entity.getAvailableBalance())
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
                .availableBalance(entity.getAvailableBalance())
                .currency(CurrencyEnum.valueOf(entity.getCurrency()))
                .type(AccountTypeEnum.valueOf(entity.getType()))
                .build();
    }

    public static AccountEntity toEntity(AccountDTO dto) {
        if (dto == null) {
            return null;
        }

        return AccountEntity.builder()
                .accountNumber(dto.getAccountNumber())
                .accountName(dto.getAccountName())
                .type(dto.getType().name())
                .currency(dto.getCurrency().name())
                .currentBalance(BigDecimal.ZERO)
                .availableBalance(BigDecimal.ZERO)
                .build();
    }
}
