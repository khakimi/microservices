package com.example.userserver.repository;

import com.example.userserver.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    void findUserByPhoneNumberTest() {
        //given
        String testPhoneNumber = "1234567";
        User testUser = new User(testPhoneNumber);
        //when
        userRepository.save(testUser);
        //then
        assertEquals(testUser, userRepository.findUserByPhoneNumber(testPhoneNumber).get());
    }
}