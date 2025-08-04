package app.budgetanalytics.api.mapper;

import app.budgetanalytics.api.dto.CreateTransactionDto;
import app.budgetanalytics.api.dto.TransactionDto;
import app.budgetanalytics.api.dto.UpdateTransactionDto;
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

    public static TransactionDto toDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setAccountId(transaction.getAccount().getId());
        dto.setDescription(transaction.getDescription());
        dto.setDate(transaction.getDate());
        dto.setCategoryId(transaction.getCategory().getId());
        dto.setAmount(transaction.getAmount());
        return dto;
    }

    public static Transaction toEntity(UpdateTransactionDto dto, Category category, Account account, Transaction transaction) {
        transaction.setDescription(dto.getDescription());
        transaction.setDate(dto.getDate());
        transaction.setAmount(dto.getAmount());
        transaction.setCategory(category);
        transaction.setAccount(account);
        return transaction;
    }
}
