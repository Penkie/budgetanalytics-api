package app.budgetanalytics.api.dto;

import lombok.Data;

@Data
public class AccountDto {

    private String name;

    private long amount;

    private String type;

    private String icon;
}
