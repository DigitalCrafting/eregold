package org.digitalcrafting.eregold.repository.clients.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakeTransactionResponse {
    private Long id;
    private String accountNumber;
}
