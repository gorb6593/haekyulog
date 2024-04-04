package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Users;
import com.haekyulog.haekyulog.repository.UserRepository;
import com.haekyulog.haekyulog.requesst.Signup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void test1() {
        //given
        Signup signup = Signup.builder()
                .email("gorb6593@naver.com")
                .password("1234")
                .build();

        //when
        authService.signup(signup);

        //then
        assertEquals(1, userRepository.count());

        Users users = userRepository.findAll().iterator().next();
        assertEquals("gorb6593@naver.com", users.getEmail());
        assertEquals("1234", users.getPassword());
        //assertEquals("해규", users.getName());

    }

}