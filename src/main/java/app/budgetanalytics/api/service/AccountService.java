package app.budgetanalytics.api.service;

import app.budgetanalytics.api.dto.CreateAccountDto;
import app.budgetanalytics.api.dto.UpdateAccountDto;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.helper.VerifyOwnershipHelper;
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
    private final VerifyOwnershipHelper verifyOwnershipHelper;

    public AccountService(AccountRepository accountRepository, VerifyOwnershipHelper verifyOwnershipHelper) {
        this.accountRepository = accountRepository;
        this.verifyOwnershipHelper = verifyOwnershipHelper;
    }

    public List<Account> findAccounts(String userId) {
        return this.accountRepository.findAllByUserId(userId);
    }

    @Transactional
    public Account save(CreateAccountDto dto, String userId) {
        Account account = AccountMapper.toEntity(dto, userId);
        return this.accountRepository.save(account);
    }

    @Transactional
    public Account updateAccount(UpdateAccountDto dto, String userId) {
        // check user is writing on his own content
        Account account = this.verifyOwnershipHelper.getOwnedAccountOrThrow(dto.getId(), userId);
        AccountMapper.updateEntityValues(dto, account);
        return this.accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(String id, String userId) {
        Account account = this.verifyOwnershipHelper.getOwnedAccountOrThrow(id, userId);
        this.accountRepository.deleteById(account.getId());
    }

}
