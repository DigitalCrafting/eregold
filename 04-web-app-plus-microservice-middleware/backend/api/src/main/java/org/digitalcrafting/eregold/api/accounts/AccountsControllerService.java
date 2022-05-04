package org.digitalcrafting.eregold.api.accounts;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.authentication.EregoldSessionContext;
import org.digitalcrafting.eregold.domain.accounts.AccountDetailsModel;
import org.digitalcrafting.eregold.domain.accounts.AccountModel;
import org.digitalcrafting.eregold.domain.accounts.AccountsConverter;
import org.digitalcrafting.eregold.domain.transactions.TransactionHistoryModel;
import org.digitalcrafting.eregold.domain.transactions.TransactionsConverter;
import org.digitalcrafting.eregold.repository.clients.accounts.AccountDTO;
import org.digitalcrafting.eregold.repository.clients.accounts.AccountsClient;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionDTO;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionsClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsControllerService {

    private final TransactionsClient transactionsClient;
    private final AccountsClient accountsClient;
    private final EregoldSessionContext sessionContext;

    public List<AccountModel> getAccounts() {
        List<AccountDTO> entityList = accountsClient.getAccountsForCustomer(sessionContext.getCustomerId());
        return AccountsConverter.toModelList(entityList);
    }

    public void createAccount(CreateAccountRequest request) {
        AccountDTO accountDTO = AccountDTO.builder()
                .accountNumber(generateAccountNumber())
                .accountName(request.getAccountName())
                .type(request.getAccountType().name())
                .currency(request.getCurrency().name())
                .currentBalance(BigDecimal.ZERO)
                .build();
        accountsClient.createAccount(accountDTO, sessionContext.getCustomerId());
    }

    private String generateAccountNumber() {
        StringBuilder number;
        do {
            number = new StringBuilder("12ERGD");
            // toEpochMilli should return number of length 13 at the time of writing this code, so we want to limit ourselves to 12
            number.append(String.valueOf(Instant.now().toEpochMilli()).substring(0, 12));
        } while (accountsClient.getByAccountNumber(number.toString()) != null);
        return number.toString();
    }

    public AccountDetailsModel getAccountDetails(String accountNumber) {
        AccountDTO entity = accountsClient.getByAccountNumber(accountNumber);

        if (entity == null) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }

        AccountDetailsModel detailsModel = AccountsConverter.toDetailsModel(entity);
        List<TransactionDTO> transactionDTOList = transactionsClient.getByAccountNumber(accountNumber);
        List<TransactionHistoryModel> transactionHistoryModelList = TransactionsConverter.toModelList(transactionDTOList);
        transactionHistoryModelList.sort(new TransactionHistoryModel.DateDescendingComparator());
        detailsModel.setTransactionsList(transactionHistoryModelList);

        return detailsModel;
    }
}
