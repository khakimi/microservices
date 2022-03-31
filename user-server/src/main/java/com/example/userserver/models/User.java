package com.example.userserver.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
