package org.digitalcrafting.arkenstone.api.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transactions")
public class TransactionsController {
    private final TransactionsControllerService service;
}
