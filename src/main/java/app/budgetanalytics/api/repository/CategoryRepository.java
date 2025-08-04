package app.budgetanalytics.api.repository;

import app.budgetanalytics.api.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, String> {

    List<Category> findAllByUserId(String userId);
}
