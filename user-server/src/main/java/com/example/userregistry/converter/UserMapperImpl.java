package com.example.userregistry.converter;

import com.example.userregistry.dto.UserDTO;
import com.example.userregistry.models.User;

public class UserMapperImpl implements UserMapper{
    @Override
    public User DTOtoEntity(UserDTO userDTO) {
        return User.builder()
                .phoneNumber(userDTO.getPhoneNumber())
                .patronymic(userDTO.getPatronymic())
                .passportNumber(userDTO.getPassportNumber())
                .passportIssueDate(userDTO.getPassportIssueDate())
                .name(userDTO.getName())
                .monthlyIncome(userDTO.getMonthlyIncome())
                .monthlyExpensesOnAlimonyAndCredits(userDTO.getMonthlyExpensesOnAlimonyAndCredits())
                .maritalStatus(userDTO.getMaritalStatus())
                .lastname(userDTO.getLastname())
                .incomeVerificationMethod(userDTO.getIncomeVerificationMethod())
                .birthDate(userDTO.getBirthDate())
                .build();

    }

    @Override
    public UserDTO EntitytoDTO(User user) {
        return UserDTO.builder()
                .phoneNumber(user.getPhoneNumber())
                .patronymic(user.getPatronymic())
                .passportNumber(user.getPassportNumber())
                .passportIssueDate(user.getPassportIssueDate())
                .name(user.getName())
                .monthlyIncome(user.getMonthlyIncome())
                .monthlyExpensesOnAlimonyAndCredits(user.getMonthlyExpensesOnAlimonyAndCredits())
                .maritalStatus(user.getMaritalStatus())
                .lastname(user.getLastname())
                .incomeVerificationMethod(user.getIncomeVerificationMethod())
                .birthDate(user.getBirthDate())
                .build();
    }
}
