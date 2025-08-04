package app.budgetanalytics.api.mapper;

import app.budgetanalytics.api.dto.CreateTransactionDto;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.entity.Category;
import app.budgetanalytics.api.entity.Transaction;

public class TransactionMapper {

    public static Transaction toEntity(CreateTransactionDto dto, Account account, Category category, String userId) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setCategory(category);
        transaction.setDate(dto.getDate());
        transaction.setAmount(dto.getAmount());
        transaction.setDescription(dto.getDescription());
        transaction.setUserId(userId);
        return transaction;
    }
}
