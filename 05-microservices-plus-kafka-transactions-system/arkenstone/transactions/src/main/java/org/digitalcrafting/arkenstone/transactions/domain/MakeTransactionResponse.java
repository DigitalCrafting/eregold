package org.digitalcrafting.arkenstone.transactions.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeTransactionResponse {
    private Long id;
    private String accountNumber;
}
