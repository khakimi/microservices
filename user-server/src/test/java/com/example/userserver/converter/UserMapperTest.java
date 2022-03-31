package com.example.userserver.converter;

import com.example.userserver.dto.UserDTO;
import com.example.userserver.models.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;

    User testUser;

    UserDTO testUserDTO;

    @BeforeEach
    void init(){
        //given
        Date testIssueDate = new Date();
        Date testBirthDate = new Date();
        userMapper = new UserMapperImpl();
        testUser = User.builder()
                .name("testName")
                .lastname("testLastname")
                .patronymic("testPatronymic")
                .email("testEmail")
                .passportNumber("testPassport")
                .passportIssueDate(testIssueDate)
                .birthDate(testBirthDate)
                .incomeVerificationMethod("testIncomeVerificationMethod")
                .maritalStatus("testMaritalStatus")
                .monthlyIncome(new BigDecimal(100))
                .monthlyExpensesOnAlimonyAndCredits(new BigDecimal(10))
                .phoneNumber("1234567")
                .build();
        testUserDTO = UserDTO.builder()
                .name("testName")
                .lastname("testLastname")
                .patronymic("testPatronymic")
                .email("testEmail")
                .passportNumber("testPassport")
                .passportIssueDate(testIssueDate)
                .birthDate(testBirthDate)
                .incomeVerificationMethod("testIncomeVerificationMethod")
                .maritalStatus("testMaritalStatus")
                .monthlyIncome(new BigDecimal(100))
                .monthlyExpensesOnAlimonyAndCredits(new BigDecimal(10))
                .phoneNumber("1234567")
                .build();
    }

    @Test
    void DTOtoEntityTest() {
        //when
        User userConverted = userMapper.DTOtoEntity(testUserDTO);
        //then
        assertEquals(testUser, userConverted);
    }

    @Test
    void entityToDTOTest() {
        //when
        UserDTO userDTOConverted = userMapper.EntityToDTO(testUser);
        //then
        assertEquals(testUserDTO, userDTOConverted);
    }
}