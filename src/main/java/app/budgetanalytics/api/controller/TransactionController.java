package app.budgetanalytics.api.controller;

import app.budgetanalytics.api.dto.CreateTransactionDto;
import app.budgetanalytics.api.entity.Transaction;
import app.budgetanalytics.api.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@AuthenticationPrincipal Jwt jwt, @PathVariable String id) {
        this.transactionService.deleteTransaction(id, jwt.getSubject());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
