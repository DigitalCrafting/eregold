package org.digitalcrafting.arkenstone.domain.transactions;


import org.digitalcrafting.arkenstone.domain.accounts.CurrencyEnum;
import org.digitalcrafting.arkenstone.repository.transactions.TransactionEntity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TransactionsConverter {
    private TransactionsConverter() {
    }

    public static List<TransactionHistoryDTO> toDTOList(List<TransactionEntity> entityList) {
        List<TransactionHistoryDTO> modelList = null;

        if (entityList != null && !entityList.isEmpty()) {
            modelList = entityList.stream()
                    .map(TransactionsConverter::toDTO)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return modelList;
    }

    public static TransactionHistoryDTO toDTO(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }

        return TransactionHistoryDTO.builder()
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

    public static TransactionEntity toSrcTransferEntity(TransactionDTO transfer, Date date) {
        TransactionEntity entity = new TransactionEntity();

        entity.setAccountNumber(transfer.getSrcAccount());
        entity.setForeignAccountNumber(transfer.getDstAccount());
        entity.setDescription(transfer.getDescription());
        entity.setType(TransactionTypeEnum.TRANSFER.name());
        entity.setCurrency(transfer.getCurrency().name());
        entity.setAmount(transfer.getAmount().negate());
        entity.setDate(date);

        return entity;
    }

    public static TransactionEntity toDstTransferEntity(TransactionDTO transfer, Date date) {
        TransactionEntity entity = new TransactionEntity();

        entity.setAccountNumber(transfer.getDstAccount());
        entity.setForeignAccountNumber(transfer.getSrcAccount());
        entity.setDescription(transfer.getDescription());
        entity.setType(TransactionTypeEnum.TRANSFER.name());
        entity.setCurrency(transfer.getCurrency().name());
        entity.setAmount(transfer.getAmount());
        entity.setDate(date);

        return entity;
    }


    public static TransactionEntity toDepositEntity(TransactionDTO transfer, Date date) {
        TransactionEntity entity = new TransactionEntity();

        entity.setAccountNumber(transfer.getDstAccount());
        entity.setDescription(transfer.getDescription());
        entity.setType(TransactionTypeEnum.DEPOSIT.name());
        entity.setCurrency(transfer.getCurrency().name());
        entity.setAmount(transfer.getAmount());
        entity.setDate(date);

        return entity;
    }
}
