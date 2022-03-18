package org.digitalcrafting.eregold.api.transfers;

import org.digitalcrafting.eregold.domain.transactions.TransactionTypeEnum;
import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;

import java.util.Date;

public final class TransferConverter {
    public static TransactionEntity convert(TransferRequest transfer) {
        TransactionEntity entity = new TransactionEntity();

        entity.setSrcAccount(transfer.getSrcAccount());
        entity.setDstAccount(transfer.getDstAccount());
        entity.setDescription(transfer.getDescription());
        entity.setType(TransactionTypeEnum.TRANSFER.name());
        entity.setCurrency(transfer.getCurrency().name());
        entity.setAmount(transfer.getAmount());
        entity.setDate(new Date());

        return entity;
    }
}
