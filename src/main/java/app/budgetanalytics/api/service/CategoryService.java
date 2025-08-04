package app.budgetanalytics.api.service;

import app.budgetanalytics.api.dto.CreateCategoryDto;
import app.budgetanalytics.api.dto.UpdateCategoryDto;
import app.budgetanalytics.api.entity.Category;
import app.budgetanalytics.api.helper.VerifyOwnershipHelper;
import app.budgetanalytics.api.mapper.CategoryMapper;
import app.budgetanalytics.api.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final VerifyOwnershipHelper verifyOwnershipHelper;

    public CategoryService(CategoryRepository categoryRepository, VerifyOwnershipHelper verifyOwnershipHelper) {
        this.categoryRepository = categoryRepository;
        this.verifyOwnershipHelper = verifyOwnershipHelper;
    }

    public List<Category> findCategories(String userId) {
        return this.categoryRepository.findAllByUserId(userId);
    }

    @Transactional
    public Category save(CreateCategoryDto dto, String userId) {
        Category category = CategoryMapper.toEntity(dto, userId);
        return this.categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(UpdateCategoryDto dto, String userId) {
        // check user is writing on his own content
        Category category = this.verifyOwnershipHelper.getOwnedCategoryOrThrow(dto.getId(), userId);
        CategoryMapper.updateEntityValues(dto, category);
        return this.categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(String id, String userId) {
        Category category = this.verifyOwnershipHelper.getOwnedCategoryOrThrow(id, userId);
        this.categoryRepository.deleteById(category.getId());
    }
}
