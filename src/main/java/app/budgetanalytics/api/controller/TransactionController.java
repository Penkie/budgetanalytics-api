package app.budgetanalytics.api.controller;

import app.budgetanalytics.api.dto.CreateTransactionDto;
import app.budgetanalytics.api.entity.Transaction;
import app.budgetanalytics.api.service.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping()
    public Transaction createTransaction(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateTransactionDto dto) {
        return this.transactionService.createTransaction(dto, jwt.getSubject());
    }
}
