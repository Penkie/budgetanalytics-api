package app.budgetanalytics.api.repository;

import app.budgetanalytics.api.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAllByUserId(String userId);
}
