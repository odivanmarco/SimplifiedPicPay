package com.simplifiedpicpay.services.Interfaces;

import java.math.BigDecimal;

import com.simplifiedpicpay.domain.user.User;

public interface IUserService {
    public void validateTransaction(User sender, BigDecimal amount) throws Exception;
    public User findUserById(Long id) throws Exception;
    public void saveUser(User user);
}
