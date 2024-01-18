package com.simplifiedpicpay.dtos;

import java.math.BigDecimal;

import com.simplifiedpicpay.domain.user.UserTypeEnum;

public record UserDto(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserTypeEnum userType) {}
