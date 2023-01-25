package com.Elysyum.controller;


import com.Elysyum.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class BaseControllerTest<T>{

    @Autowired
    MockMvc mockMvc;

    MvcResult mvcResult;

    @Autowired
    JacksonTester<T> jacksonTester;

    UserDto userDto = new UserDto(1L,"Mihai","1234"
            ,"mimi@gmail.com");

}
