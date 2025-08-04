package app.budgetanalytics.api.service;

import app.budgetanalytics.api.dto.CreateTransactionDto;
import app.budgetanalytics.api.dto.UpdateTransactionDto;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.entity.Category;
import app.budgetanalytics.api.entity.Transaction;
import app.budgetanalytics.api.helper.VerifyOwnershipHelper;
import app.budgetanalytics.api.mapper.TransactionMapper;
import app.budgetanalytics.api.repository.AccountRepository;
import app.budgetanalytics.api.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final VerifyOwnershipHelper verifyOwnershipHelper;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, VerifyOwnershipHelper verifyOwnershipHelper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.verifyOwnershipHelper = verifyOwnershipHelper;
    }

    @Transactional
    public Transaction createTransaction(CreateTransactionDto dto, String userId) {
        Account account = verifyOwnershipHelper.getOwnedAccountOrThrow(dto.getAccountId(), userId);
        Category category = verifyOwnershipHelper.getOwnedCategoryOrThrow(dto.getCategoryId(), userId);
        Transaction transaction = TransactionMapper.toEntity(dto, account, category, userId);

        // update account value from transaction amount
        account.setAmount(account.getAmount() + transaction.getAmount());
        this.accountRepository.save(account);

        return this.transactionRepository.save(transaction);
    }

    @Transactional
    public void deleteTransaction(String id, String userId) {
        Transaction transaction = verifyOwnershipHelper.getOwnedTransactionOrThrow(id, userId);
        this.transactionRepository.deleteById(transaction.getId());
    }

    @Transactional
    public Transaction updateTransaction(UpdateTransactionDto dto, String userId) {
        Transaction transaction = verifyOwnershipHelper.getOwnedTransactionOrThrow(dto.getId(), userId);
        Account account = verifyOwnershipHelper.getOwnedAccountOrThrow(dto.getAccountId(), userId);
        Category category = verifyOwnershipHelper.getOwnedCategoryOrThrow(dto.getCategoryId(), userId);

        // If account changed in the update - change old account value and new account value
        if (!transaction.getAccount().getId().equals(account.getIcon())) {
            transaction.getAccount().setAmount(transaction.getAccount().getAmount() - transaction.getAmount());
            accountRepository.save(transaction.getAccount());
        }

        Transaction newTransaction = TransactionMapper.toEntity(dto, category, account, transaction);

        // update account value from transaction amount
        account.setAmount(account.getAmount() + transaction.getAmount());
        this.accountRepository.save(account);

        return this.transactionRepository.save(transaction);
    }

}
