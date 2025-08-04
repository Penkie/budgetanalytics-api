package app.budgetanalytics.api.service;

import app.budgetanalytics.api.dto.CreateAccountDto;
import app.budgetanalytics.api.dto.UpdateAccountDto;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.mapper.AccountMapper;
import app.budgetanalytics.api.repository.AccountRepository;
import jakarta.transaction.Transactional;
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
        Account account = this.getOwnedAccountOrThrow(dto.getId(), userId);
        AccountMapper.updateEntityValues(dto, account);
        return this.accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(String id, String userId) {
        Account account = this.getOwnedAccountOrThrow(id, userId);
        this.accountRepository.deleteById(account.getId());
    }

    private Account getOwnedAccountOrThrow(String id, String userId) {
        Account account = this.accountRepository.findById(id);
        if (account != null && account.getUserId().equals(userId)) {
            return account;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have access to this resource or it doesn't exist.");
        }
    }
}
