package app.budgetanalytics.api.controller;

import app.budgetanalytics.api.dto.AccountDto;
import app.budgetanalytics.api.dto.CreateAccountDto;
import app.budgetanalytics.api.entity.Account;
import app.budgetanalytics.api.mapper.AccountMapper;
import app.budgetanalytics.api.service.AccountService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public List<AccountDto> getAccounts(@AuthenticationPrincipal Jwt jwt) {
        List<Account> accounts = this.accountService.findAccounts(jwt.getSubject());
        return accounts.stream()
                .map(AccountMapper::toDto)
                .toList();
    }

    @PostMapping()
    public AccountDto saveAccount(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateAccountDto dto) {
        Account account = this.accountService.save(dto, jwt.getSubject());
        return AccountMapper.toDto(account);
    }
}