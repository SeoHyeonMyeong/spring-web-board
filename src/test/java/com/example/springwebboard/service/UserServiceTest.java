package com.example.springwebboard.service;

import com.example.springwebboard.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("유저 상세 테스트")
    void detail() {
        // given


        // when


        // then


    }

    @Test
    @DisplayName("유저 생성 테스트")
    @Transactional
    void createUser() {
        // given
        UserDto dto = new UserDto("user1", "password", "master");

        // when
        UserDto created = userService.createUser(dto);

        // then
        assertAll(
                () -> assertEquals(dto.getName(), created.getName()),
                () -> assertTrue(passwordEncoder.matches(dto.getPassword(), created.getPassword())),
                () -> assertEquals(dto.getNickname(), created.getNickname())
        );
    }
}