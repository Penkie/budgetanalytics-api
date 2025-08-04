package app.budgetanalytics.api.mapper;

import app.budgetanalytics.api.dto.*;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CreateCategoryDto dto, String userId) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setColor(dto.getColor());
        category.setIcon(dto.getIcon());
        category.setUserId(userId);
        return category;
    }

    public static Category updateEntityValues(UpdateCategoryDto dto, Category category) {
        category.setName(dto.getName());
        category.setColor(dto.getColor());
        category.setIcon(dto.getIcon());
        return category;
    }

    public static CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setColor(category.getColor());
        dto.setIcon(category.getIcon());
        dto.setName(category.getName());
        return dto;
    }
}
