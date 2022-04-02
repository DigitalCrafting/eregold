package org.digitalcrafting.eregold.domain.transactions;

import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TransactionsConverter {
    private TransactionsConverter() {
    }

    public static List<TransactionModel> toModelList(List<TransactionEntity> entityList) {
        List<TransactionModel> modelList = null;

        if (entityList != null && !entityList.isEmpty()) {
            modelList = entityList.stream()
                    .map(TransactionsConverter::toModel)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return modelList;
    }

    public static TransactionModel toModel(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }

        return TransactionModel.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .foreignAccountNumber(entity.getForeignAccountNumber())
                .description(entity.getDescription())
                .type(entity.getType())
                .currency(entity.getCurrency())
                .amount(entity.getAmount())
                .date(entity.getDate())
                .build();
    }
}
