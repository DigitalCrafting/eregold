package org.digitalcrafting.arkenstone.transactionVerification.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaTransactionMessage {
    private String id = UUID.randomUUID().toString();
    private LocalDate messageDate = LocalDate.now();
    private Long transactionId;
    private String accountNumber;
}
