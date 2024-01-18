package com.simplifiedpicpay.controllers;

import com.simplifiedpicpay.domain.transaction.Transaction;
import com.simplifiedpicpay.dtos.TransactionDto;
import com.simplifiedpicpay.services.Interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    private ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto dto) throws Exception {
        Transaction transaction = this.transactionService.createTransaction(dto);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<Transaction>> getAllTransaction(){
        List<Transaction> listTransactions = this.transactionService.getAll();
        return new ResponseEntity<>(listTransactions, HttpStatus.OK);
    }
}
