package org.digitalcrafting.arkenstone.transactionVerification.domain;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactionVerification.repository.accounts.AccountEntity;
import org.digitalcrafting.arkenstone.transactionVerification.repository.accounts.AccountsMapper;
import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionEntity;
import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionVerificationService {
    private final AccountsMapper accountsMapper;
    private final TransactionsMapper transactionsMapper;

    public AccountEntity getAccount() {
        return accountsMapper.getByAccountNumber("12ERGD165796912095");
    }

    public List<TransactionEntity> getTransaction() {
        return transactionsMapper.getByAccountNumber("12ERGD165796912095");
    }
}
