package app.budgetanalytics.api.controller;

import app.budgetanalytics.api.dto.*;
import app.budgetanalytics.api.entity.Category;
import app.budgetanalytics.api.mapper.CategoryMapper;
import app.budgetanalytics.api.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDto> getCategories(@AuthenticationPrincipal Jwt jwt) {
        List<Category> categories = this.categoryService.findCategories(jwt.getSubject());
        return categories.stream()
                .map(CategoryMapper::toDto)
                .toList();
    }

    @PostMapping()
    public CategoryDto saveCategory(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateCategoryDto dto) {
        Category category = this.categoryService.save(dto, jwt.getSubject());
        return CategoryMapper.toDto(category);
    }

    @PutMapping()
    public CategoryDto updateCategory(@AuthenticationPrincipal Jwt jwt, @RequestBody UpdateCategoryDto dto) {
        Category category = this.categoryService.updateCategory(dto, jwt.getSubject());
        return CategoryMapper.toDto(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@AuthenticationPrincipal Jwt jwt, @PathVariable String id) {
        this.categoryService.deleteCategory(id, jwt.getSubject());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}