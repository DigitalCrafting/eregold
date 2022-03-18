package org.digitalcrafting.eregold.api.transfers;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.repository.accounts.AccountsEntityManager;
import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;
import org.digitalcrafting.eregold.repository.transactions.TransactionsMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransfersControllerService {

    private final TransactionsMapper transactionsMapper;
    private final AccountsEntityManager accountsEntityManager;

    public void transfer(TransferRequest request) {
        TransactionEntity entity = TransferConverter.convert(request);
        // TODO verify amount and save to db
    }
}
