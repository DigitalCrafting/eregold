package org.digitalcrafting.arkenstone.domain.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.digitalcrafting.arkenstone.domain.accounts.CurrencyEnum;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryDTO {
    private Long id;
    private String accountNumber;
    private String foreignAccountNumber;
    private String description;
    private TransactionTypeEnum type;
    private CurrencyEnum currency;
    private BigDecimal amount;
    private Date date;

    public static class DateDescendingComparator implements Comparator<TransactionHistoryDTO> {
        @Override
        public int compare(TransactionHistoryDTO o1, TransactionHistoryDTO o2) {
            return o2.getDate().compareTo(o1.getDate());
        }
    }
}
