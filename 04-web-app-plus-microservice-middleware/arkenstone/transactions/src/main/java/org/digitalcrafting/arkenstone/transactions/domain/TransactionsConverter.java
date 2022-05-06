package org.digitalcrafting.arkenstone.transactions.domain;

import org.digitalcrafting.arkenstone.transactions.repository.TransactionEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TransactionsConverter {
    private TransactionsConverter() {
    }

    public static List<TransactionDTO> toDTOList(List<TransactionEntity> entityList) {
        List<TransactionDTO> modelList = null;

        if (entityList != null && !entityList.isEmpty()) {
            modelList = entityList.stream()
                    .map(TransactionsConverter::toDTO)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return modelList;
    }

    public static TransactionDTO toDTO(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }

        return TransactionDTO.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .foreignAccountNumber(entity.getForeignAccountNumber())
                .description(entity.getDescription())
                .type(TransactionTypeEnum.valueOf(entity.getType()))
                .currency(CurrencyEnum.valueOf(entity.getCurrency()))
                .amount(entity.getAmount())
                .date(entity.getDate())
                .build();
    }

    public static List<TransactionEntity> toEntityList(List<TransactionDTO> dtoList) {
        List<TransactionEntity> entityList = null;

        if (dtoList != null && !dtoList.isEmpty()) {
            entityList = dtoList.stream()
                    .map(TransactionsConverter::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return entityList;
    }
    public static TransactionEntity toEntity(TransactionDTO dto) {
        if (dto == null) {
            return null;
        }

        return TransactionEntity.builder()
                .id(dto.getId())
                .accountNumber(dto.getAccountNumber())
                .foreignAccountNumber(dto.getForeignAccountNumber())
                .description(dto.getDescription())
                .type(dto.getType().name())
                .currency(dto.getCurrency().name())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .build();
    }
}
