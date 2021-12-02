package org.digitalcrafting.eregold.api.accounts;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.domain.accounts.AccountModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/accounts")
public class AccountsController {

    private final AccountsControllerService service;

    @GetMapping
    @ResponseBody
    public List<AccountModel> getAccounts() {
        return service.getAccounts();
    }
}
