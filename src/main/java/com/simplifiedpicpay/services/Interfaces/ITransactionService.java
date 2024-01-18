package com.simplifiedpicpay.services.Interfaces;

import com.simplifiedpicpay.dtos.TransactionDto;

public interface ITransactionService {
    public void createTransaction(TransactionDto dto) throws Exception;
}
