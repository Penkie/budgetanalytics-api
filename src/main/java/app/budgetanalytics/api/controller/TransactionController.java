package app.budgetanalytics.api.controller;

import app.budgetanalytics.api.dto.CreateTransactionDto;
import app.budgetanalytics.api.dto.TransactionDto;
import app.budgetanalytics.api.dto.UpdateTransactionDto;
import app.budgetanalytics.api.entity.Transaction;
import app.budgetanalytics.api.mapper.TransactionMapper;
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
    public TransactionDto createTransaction(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateTransactionDto dto) {
        Transaction transaction = this.transactionService.createTransaction(dto, jwt.getSubject());
        return TransactionMapper.toDto(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@AuthenticationPrincipal Jwt jwt, @PathVariable String id) {
        this.transactionService.deleteTransaction(id, jwt.getSubject());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping()
    public TransactionDto updateTransaction(@AuthenticationPrincipal Jwt jwt, @RequestBody UpdateTransactionDto dto) {
        Transaction transaction = this.transactionService.updateTransaction(dto, jwt.getSubject());
        return TransactionMapper.toDto(transaction);
    }
}
