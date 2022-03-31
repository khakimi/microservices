package com.example.userserver.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {

    private String phoneNumber;

    private String email;

    private String name;

    private String lastname;

    private String patronymic;

    private String passportNumber;

    private Date birthDate;

    private Date passportIssueDate;

    private String maritalStatus;

    private BigDecimal monthlyIncome;

    private BigDecimal monthlyExpensesOnAlimonyAndCredits;

    private String incomeVerificationMethod;

}