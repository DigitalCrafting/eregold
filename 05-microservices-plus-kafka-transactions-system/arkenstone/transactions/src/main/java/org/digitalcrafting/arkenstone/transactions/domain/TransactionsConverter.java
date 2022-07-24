package org.digitalcrafting.arkenstone.transactions.domain;

import org.digitalcrafting.arkenstone.transactions.repository.db.TransactionEntity;

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
                .status(TransactionStatusEnum.valueOf(entity.getStatus()))
                .amount(entity.getAmount())
                .date(entity.getDate())
                .build();
    }

    public static TransactionEntity toDepositEntity(TransactionDTO dto) {
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
                .status(TransactionStatusEnum.ACCEPTED.name())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .build();
    }

    public static TransactionEntity toTransferEntity(TransactionDTO dto) {
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
                .status(TransactionStatusEnum.PENDING.name())
                .amount(dto.getAmount().negate())
                .date(dto.getDate())
                .build();
    }
}
