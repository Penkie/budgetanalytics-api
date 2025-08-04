package app.budgetanalytics.api.repository;

import app.budgetanalytics.api.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Page<Transaction> findByDateBetweenAndUserId(Date startDate, Date endDate, String userId, Pageable pageable);
}
