package app.budgetanalytics.api.repository;

import app.budgetanalytics.api.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
