package org.digitalcrafting.eregold.domain.accounts;

import org.digitalcrafting.eregold.repository.clients.accounts.AccountDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class AccountsConverter {
    private AccountsConverter() {}

    public static List<AccountModel> toModelList(List<AccountDTO> dtoList) {
        List<AccountModel> accountModelList = null;

        if (dtoList != null && !dtoList.isEmpty()) {
            accountModelList = dtoList.stream()
                    .map(AccountsConverter::toModel)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return accountModelList;
    }

    public static AccountModel toModel(AccountDTO dto) {
        if (dto == null) {
            return null;
        }

        return AccountModel.builder()
                .accountNumber(dto.getAccountNumber())
                .accountName(dto.getAccountName())
                .currency(CurrencyEnum.valueOf(dto.getCurrency()))
                .type(AccountTypeEnum.valueOf(dto.getType()))
                .currentBalance(dto.getCurrentBalance())
                .build();
    }

    public static AccountDetailsModel toDetailsModel(AccountDTO dto) {
        if (dto == null) {
            return null;
        }

        return AccountDetailsModel.builder()
                .accountNumber(dto.getAccountNumber())
                .accountName(dto.getAccountName())
                .currentBalance(dto.getCurrentBalance())
                .currency(CurrencyEnum.valueOf(dto.getCurrency()))
                .type(AccountTypeEnum.valueOf(dto.getType()))
                .build();
    }
}
