package app.budgetanalytics.api.dto;

import lombok.Data;

@Data
public class AccountDto {

    private String id;

    private String name;

    private long amount;

    private String type;

    private String icon;
}
