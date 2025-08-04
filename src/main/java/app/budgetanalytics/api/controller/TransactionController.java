package app.budgetanalytics.api.controller;

import app.budgetanalytics.api.dto.CreateTransactionDto;
import app.budgetanalytics.api.dto.TransactionDto;
import app.budgetanalytics.api.dto.UpdateTransactionDto;
import app.budgetanalytics.api.entity.Transaction;
import app.budgetanalytics.api.mapper.TransactionMapper;
import app.budgetanalytics.api.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public Page<TransactionDto> getTransactions(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam int page,
            @RequestParam int size,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Page<Transaction> transactions = this.transactionService.getTransactions(startDate, endDate, jwt.getSubject(), page, size);
        return transactions.map(TransactionMapper::toDto);
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
