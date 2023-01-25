package com.Elysyum.repository;

import com.Elysyum.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UserRepositoryTest {


    private User user;

    @BeforeEach
    void setUp(){

        user = new User();
    }

    @Test
    @Commit
    @DisplayName("Saving user")
    void save_User_Success(){

        user.setId(1L);
        user.setUsername("Mihai");
        user.setPassword("1234");
        user.setEmail("mihai@gmail.com");
        assertNotNull(user);
        assertTrue(user.getId() > 0);
        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertNotNull(user.getEmail());

    }

    @Test
    void find_By_Username_Success() {

        user.setUsername("mihai");
        assertNotNull(user.getUsername());
        assertEquals("mihai",user.getUsername());

    }
}