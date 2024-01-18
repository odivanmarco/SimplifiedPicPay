package com.simplifiedpicpay.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import com.simplifiedpicpay.dtos.UserDto;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class User {
    public User(UserDto dto) {
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.document = dto.document();
        this.balance = dto.balance();
        this.userType = dto.userType();
        this.email = dto.email();
        this.password = dto.password();

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;
}
