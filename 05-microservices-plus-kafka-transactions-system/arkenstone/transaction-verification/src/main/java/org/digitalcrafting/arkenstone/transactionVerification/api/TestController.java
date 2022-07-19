package org.digitalcrafting.arkenstone.transactionVerification.api;

import org.digitalcrafting.arkenstone.transactionVerification.domain.TransactionVerificationService;
import org.digitalcrafting.arkenstone.transactionVerification.repository.accounts.AccountEntity;
import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TransactionVerificationService service;

    @GetMapping("/accounts")
    public AccountEntity getAccount() {
        return service.getAccount();
    }

    @GetMapping("/transactions")
    public List<TransactionEntity> getTransactions() {
        return service.getTransaction();
    }
}
