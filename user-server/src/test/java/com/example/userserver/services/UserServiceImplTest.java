package com.example.userserver.services;

import com.example.userserver.converter.UserMapper;
import com.example.userserver.converter.UserMapperImpl;
import com.example.userserver.dto.UserDTO;
import com.example.userserver.models.User;
import com.example.userserver.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private final UserMapper userMapper = new UserMapperImpl();

    AutoCloseable autoCloseable;


    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveUserTest() {

        User user = new User("1234567");
        //when
        userService.saveUser(userMapper.EntityToDTO(user));
        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(user, capturedUser);

    }

    @Test
    void getUserTest() {
        // given
        String phoneNumber="1234567";
        Optional<User> user = Optional.of(new User(phoneNumber));
        Mockito.when(userRepository.findUserByPhoneNumber(phoneNumber)).thenReturn(user);
        //when
        UserDTO userDTO =  userService.getUser(phoneNumber);
        //then
        verify(userRepository).findUserByPhoneNumber(phoneNumber);
        assertEquals(user.get(), userMapper.DTOtoEntity(userDTO));
    }
}