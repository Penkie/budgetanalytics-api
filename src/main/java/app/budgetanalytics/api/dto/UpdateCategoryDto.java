package app.budgetanalytics.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateCategoryDto {

    @NotNull
    private String id;

    @NotNull()
    @Length(max = 255)
    private String name;

    @NotNull()
    private String color;

    @NotNull()
    private String icon;
}
