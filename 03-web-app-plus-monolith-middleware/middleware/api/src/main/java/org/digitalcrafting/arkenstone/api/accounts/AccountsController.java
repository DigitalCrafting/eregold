package org.digitalcrafting.arkenstone.api.accounts;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/accounts")
public class AccountsController {
    private final AccountsControllerService service;
}
