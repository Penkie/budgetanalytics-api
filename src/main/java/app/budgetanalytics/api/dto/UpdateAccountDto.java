package app.budgetanalytics.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateAccountDto {

    @NotNull
    private String id;

    @NotNull()
    @Length(max = 255)
    private String name;

    @NotNull()
    private long amount;

    @NotNull()
    private String type;

    @NotNull()
    private String icon;
}
