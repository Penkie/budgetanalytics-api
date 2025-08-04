package app.budgetanalytics.api.mapper;

import app.budgetanalytics.api.dto.AccountDto;
import app.budgetanalytics.api.dto.CreateAccountDto;
import app.budgetanalytics.api.dto.UpdateAccountDto;
import app.budgetanalytics.api.entity.Account;

public class AccountMapper {

    public static Account toEntity(CreateAccountDto dto, String userId) {
        Account account = new Account();
        account.setName(dto.getName());
        account.setAmount(dto.getAmount());
        account.setType(dto.getType());
        account.setIcon(dto.getIcon());
        account.setUserId(userId);
        return account;
    }

    public static Account updateEntityValues(UpdateAccountDto dto, Account account) {
        account.setName(dto.getName());
        account.setAmount(dto.getAmount());
        account.setType(dto.getType());
        account.setIcon(dto.getIcon());
        return account;
    }

    public static AccountDto toDto(Account account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setAmount(account.getAmount());
        dto.setIcon(account.getIcon());
        dto.setName(account.getName());
        dto.setType(account.getType());
        return dto;
    }
}
