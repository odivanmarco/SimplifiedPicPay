package com.simplifiedpicpay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.simplifiedpicpay.services.Interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simplifiedpicpay.domain.transaction.Transaction;
import com.simplifiedpicpay.domain.user.User;
import com.simplifiedpicpay.dtos.TransactionDto;
import com.simplifiedpicpay.repositories.ITransactionRepository;
import com.simplifiedpicpay.services.Interfaces.ITransactionService;
import com.simplifiedpicpay.services.Interfaces.IUserService;

@Service
public class TransactionService implements ITransactionService{
    @Autowired
    private IUserService userService;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private INotificationService notificationService;

    @Autowired RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDto dto) throws Exception {
        User sender = this.userService.findUserById(dto.senderId());
        User receiver = this.userService.findUserById(dto.receiverId());

        userService.validateTransaction(sender, dto.value());

        boolean isAutorized = this.authorizeTransaction(sender, dto.value());
        if(!isAutorized){   
            throw new Exception("Transação não autorizada");
        }
        Transaction transaction = new Transaction();
            transaction.setAmount(dto.value());
            transaction.setSender(sender);
            transaction.setReceiver(receiver);
            transaction.setTimestamp(LocalDateTime.now());

            sender.setBalance(sender.getBalance().subtract(dto.value()));
            receiver.setBalance(receiver.getBalance().add(dto.value()));

            this.transactionRepository.save(transaction);
            this.userService.saveUser(sender);
            this.userService.saveUser(receiver);

            this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
            this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return transaction;
    }

    @Override
    public List<Transaction> getAll() {return this.transactionRepository.findAll();}

    private boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }
        return false;
    }
}
