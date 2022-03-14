package com.example.userregistry.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Data
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
