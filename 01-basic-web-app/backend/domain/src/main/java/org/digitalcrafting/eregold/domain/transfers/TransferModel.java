package org.digitalcrafting.eregold.domain.transfers;

import lombok.Data;

import java.util.Date;

@Data
public class TransferModel {
    private Long id;
    private String srcAccount;
    private String dstAccount;
    private Double amount;
    private String currency;
    private Date date;
    private String description;
}
