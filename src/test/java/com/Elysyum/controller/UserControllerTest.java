package com.Elysyum.controller;

import com.Elysyum.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest  extends BaseControllerTest<UserDto>{

    @Test
    @DisplayName("Finding user by ID")
    void findById_IsValid_Success() throws Exception{

        mvcResult = mockMvc.perform(get("/users")).
                andExpect(status().isOk()).andReturn();
        assertEquals(userDto.getId(),1L);
    }

    @Test
    void findAll_User_DataNotEmpty_Success() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/users"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Register User ")

    void register_ValidUser_Success() throws Exception {

        ResultActions actions = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON).content
                        (jacksonTester.write(userDto).getJson()));
        actions.andExpect(status().isCreated()).andReturn();

    }

    @Test
    @DisplayName("Update User")
    void update_UserExist_success() throws Exception {

        UserDto userDto1 = new UserDto(1L,"Mihi","23445","ana@gmail.com");

        mockMvc.perform(put("/users/{id}",1L).content(jacksonTester.write(userDto1).getJson())
                .contentType(MediaType.APPLICATION_JSON).accept
                        (MediaType.APPLICATION_JSON)).andExpect(status().isOk());



    }

    @Test
    @DisplayName("Delete user by Id")
    void deleteUser_ById_Success() throws Exception {

        ResultActions actions = mockMvc.perform(delete("/users/{id}", userDto.getId()));
        actions.andExpect(status().isOk()).andReturn();

    }
}