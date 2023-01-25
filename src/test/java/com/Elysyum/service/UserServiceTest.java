package com.Elysyum.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;


class UserServiceTest extends BaseServiceTest{


    @Test
    @DisplayName("Saving user")
    void save_newUser_Success(){

        userService.save(userDto);
        assertNotNull(userDto);


    }

    @Test
    @DisplayName("Finding users")
    void findAll_Success() {

        userService.findAll();
        assertNotNull(userDto);

    }

    @Test
    @DisplayName("Finding user by id")
    void findById_If_User_Exist_Success() {

        userService.findById(userDto.getId());
        assertThat(userDto.getId() > 0 );

    }

    @Test
    @DisplayName("Deleting user by id")
    void deleteUser_Success() {

        userService.deleteUser(userDto.getId());
        assertNotNull(userDto);


    }

    @Test
    @DisplayName("Finding user by username")
    void findByUsername_Success() {

        userService.findByUsername(userDto.getUsername());
        assertNotNull(userDto.getUsername());
        assertThat(userDto.getUsername()).isEqualTo(userDto.getUsername());
    }

}