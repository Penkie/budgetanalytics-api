package app.budgetanalytics.api.service;

import app.budgetanalytics.api.dto.CreateAccountDto;
import app.budgetanalytics.api.dto.UpdateAccountDto;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.mapper.AccountMapper;
import app.budgetanalytics.api.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Account updateAccount(UpdateAccountDto dto, String userId) {
        // check user is writing on his own content
        Account account = this.accountRepository.findById(dto.getId());
        if (account != null && account.getUserId().equals(userId)) {
            AccountMapper.updateEntityValues(dto, account);
            return this.accountRepository.save(account);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have access to this resource or it doesn't exist.");
        }
    }
}
