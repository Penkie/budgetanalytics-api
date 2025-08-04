package app.budgetanalytics.api.dto;

import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.entity.Category;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {

    private String id;

    private String description;

    private long amount;

    private Date date;

    private String categoryId;

    private String accountId;
}
