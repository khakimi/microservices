package com.example.userregistry.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String phoneNumber;
    private String email;

    private String name;
    private String lastname;
    private String patronymic;

    private String passportNumber;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date birthDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date passportIssueDate;
    private String maritalStatus;

    private BigDecimal monthlyIncome;
    private BigDecimal monthlyExpensesOnAlimonyAndCredits;
    private String incomeVerificationMethod;

    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
