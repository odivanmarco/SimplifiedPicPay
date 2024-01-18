package com.simplifiedpicpay.repositories;

import com.simplifiedpicpay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

}
