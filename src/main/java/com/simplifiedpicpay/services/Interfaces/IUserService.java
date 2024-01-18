package com.simplifiedpicpay.services.Interfaces;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.simplifiedpicpay.domain.user.User;
import com.simplifiedpicpay.dtos.UserDto;

public interface IUserService {
    public void validateTransaction(User sender, BigDecimal amount) throws Exception;
    public User findUserById(Long id) throws Exception;
    public void saveUser(User user);
    public User creatUser(UserDto dto);
    public List<User> getAll();
}
