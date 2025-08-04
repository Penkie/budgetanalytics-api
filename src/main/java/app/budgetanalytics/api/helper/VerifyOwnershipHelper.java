package app.budgetanalytics.api.helper;

import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.entity.Category;
import app.budgetanalytics.api.repository.AccountRepository;
import app.budgetanalytics.api.repository.CategoryRepository;
import app.budgetanalytics.api.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VerifyOwnershipHelper {

    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;


    public VerifyOwnershipHelper(AccountRepository accountRepository, CategoryRepository categoryRepository) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    public Category getOwnedCategoryOrThrow(String id, String userId) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        if (!category.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have access to this resource or it doesn't exist.");
        }

        return category;
    }

    public Account getOwnedAccountOrThrow(String id, String userId) {
        Account account = this.accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (!account.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have access to this resource or it doesn't exist.");
        }

        return account;
    }
}
