package org.digitalcrafting.eregold.api.transfers;

import org.digitalcrafting.eregold.domain.transactions.TransactionTypeEnum;
import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;

import java.util.Date;

public final class TransferConverter {
    public static TransactionEntity convertToSrcTransaction(TransferRequest transfer, Date date) {
        TransactionEntity entity = new TransactionEntity();

        entity.setAccountNumber(transfer.getSrcAccount());
        entity.setDstAccountNumber(transfer.getDstAccount());
        entity.setDescription(transfer.getDescription());
        entity.setType(TransactionTypeEnum.TRANSFER.name());
        entity.setCurrency(transfer.getCurrency().name());
        entity.setAmount(transfer.getAmount().negate());
        entity.setDate(date);

        return entity;
    }

    public static TransactionEntity convertToDstTransaction(TransferRequest transfer, Date date) {
        TransactionEntity entity = new TransactionEntity();

        entity.setAccountNumber(transfer.getDstAccount());
        entity.setDstAccountNumber(transfer.getSrcAccount());
        entity.setDescription(transfer.getDescription());
        entity.setType(TransactionTypeEnum.TRANSFER.name());
        entity.setCurrency(transfer.getCurrency().name());
        entity.setAmount(transfer.getAmount());
        entity.setDate(date);

        return entity;
    }
}
