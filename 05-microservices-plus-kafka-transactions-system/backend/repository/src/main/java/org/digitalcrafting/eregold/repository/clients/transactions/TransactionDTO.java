package org.digitalcrafting.eregold.repository.clients.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private String accountNumber;
    private String foreignAccountNumber;
    private String description;
    private String type;
    private String currency;
    private String status;
    private BigDecimal amount;
    private Date date;
}
