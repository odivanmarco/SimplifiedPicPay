package com.simplifiedpicpay.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplifiedpicpay.domain.user.User;
import com.simplifiedpicpay.domain.user.UserTypeEnum;
import com.simplifiedpicpay.repositories.IUserRepository;
import com.simplifiedpicpay.services.Interfaces.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserTypeEnum.MERCHANT){
            throw new Exception("Usuário do tipo logista não está autorizado a realizar transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
