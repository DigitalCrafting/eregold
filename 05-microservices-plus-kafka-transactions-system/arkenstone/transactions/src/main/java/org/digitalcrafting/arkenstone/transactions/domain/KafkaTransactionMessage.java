package org.digitalcrafting.arkenstone.transactions.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "messageDate", "transactionId", "accountNumber"})
public class KafkaTransactionMessage {
    private String id = UUID.randomUUID().toString();
    private LocalDate messageDate = LocalDate.now();
    private Long transactionId;
    private String accountNumber;

    public KafkaTransactionMessage(Long transactionId, String accountNumber) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
    }
}
