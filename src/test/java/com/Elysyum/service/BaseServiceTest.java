package com.Elysyum.service;


import com.Elysyum.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseServiceTest {

    @Autowired
    UserService userService;

    public UserDto userDto;


    @BeforeEach
    void setUp(){

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("Mihai");
        userDto.setPassword("Jansky");
        userDto.setEmail("mihai@gmail.com");

        userService.save(userDto);


    }
}
