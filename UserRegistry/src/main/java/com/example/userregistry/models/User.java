package com.example.userregistry.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(unique=true)
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


    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User(Long id, String phoneNumber, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.authorities = authorities;
    }
    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
