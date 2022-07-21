package org.digitalcrafting.arkenstone.transactionVerification.domain;

import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionEntity;

public class TransactionConverter {
    public static TransactionEntity createDstTransaction(TransactionEntity srcEntity) {
        return TransactionEntity.builder()
                .id(srcEntity.getId())
                .accountNumber(srcEntity.getForeignAccountNumber())
                .foreignAccountNumber(srcEntity.getAccountNumber())
                .description(srcEntity.getDescription())
                .type(srcEntity.getType())
                .currency(srcEntity.getCurrency())
                .status(srcEntity.getStatus())
                .amount(srcEntity.getAmount().negate())
                .date(srcEntity.getDate())
                .build();

    }
}
