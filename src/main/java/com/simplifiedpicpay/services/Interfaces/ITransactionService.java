package com.simplifiedpicpay.services.Interfaces;

import com.simplifiedpicpay.domain.transaction.Transaction;
import com.simplifiedpicpay.dtos.TransactionDto;

import java.util.List;

public interface ITransactionService {
    public Transaction createTransaction(TransactionDto dto) throws Exception;
    public List<Transaction> getAll();
}
