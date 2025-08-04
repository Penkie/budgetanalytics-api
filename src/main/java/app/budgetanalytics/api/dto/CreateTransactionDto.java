package app.budgetanalytics.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreateTransactionDto {

    @NotNull
    private String description;

    @NotNull
    private long amount;

    @NotNull
    private Date date;

    @NotNull
    private String categoryId;

    @NotNull
    private String accountId;
}
