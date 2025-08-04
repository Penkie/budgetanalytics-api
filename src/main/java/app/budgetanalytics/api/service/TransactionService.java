package app.budgetanalytics.api.service;

import app.budgetanalytics.api.dto.CreateTransactionDto;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.entity.Category;
import app.budgetanalytics.api.entity.Transaction;
import app.budgetanalytics.api.helper.VerifyOwnershipHelper;
import app.budgetanalytics.api.mapper.TransactionMapper;
import app.budgetanalytics.api.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final VerifyOwnershipHelper verifyOwnershipHelper;

    public TransactionService(TransactionRepository transactionRepository, VerifyOwnershipHelper verifyOwnershipHelper) {
        this.transactionRepository = transactionRepository;
        this.verifyOwnershipHelper = verifyOwnershipHelper;
    }

    @Transactional
    public Transaction createTransaction(CreateTransactionDto dto, String userId) {
        Account account = verifyOwnershipHelper.getOwnedAccountOrThrow(dto.getAccountId(), userId);
        Category category = verifyOwnershipHelper.getOwnedCategoryOrThrow(dto.getCategoryId(), userId);
        Transaction transaction = TransactionMapper.toEntity(dto, account, category, userId);
        return this.transactionRepository.save(transaction);
    }

    @Transactional
    public void deleteTransaction(String id, String userId) {
        Transaction transaction = verifyOwnershipHelper.getOwnedTransactionOrThrow(id, userId);
        this.transactionRepository.deleteById(transaction.getId());
    }

}
