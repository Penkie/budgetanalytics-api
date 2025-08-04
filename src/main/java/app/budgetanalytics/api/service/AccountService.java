package app.budgetanalytics.api.service;

import app.budgetanalytics.api.dto.CreateAccountDto;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.mapper.AccountMapper;
import app.budgetanalytics.api.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAccounts(String userId) {
        return this.accountRepository.findAllByUserId(userId);
    }

    public Account save(CreateAccountDto dto, String userId) {
        Account account = AccountMapper.toEntity(dto, userId);
        return this.accountRepository.save(account);
    }
}
