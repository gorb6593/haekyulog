package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Users;
import com.haekyulog.haekyulog.exception.AlreadyExistsEmailException;
import com.haekyulog.haekyulog.repository.UserRepository;
import com.haekyulog.haekyulog.requesst.Signup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
                .name("해규")
                .build();

        //when
        authService.signup(signup);

        //then
        assertEquals(1, userRepository.count());

        Users users = userRepository.findAll().iterator().next();
        assertEquals("gorb6593@naver.com", users.getEmail());
        assertEquals("1234", users.getPassword());
        assertEquals("해규", users.getName());
    }

    @Test
    @DisplayName("회원가입시 중복된 이메일")
    void test2() {
        //given
        Users users = Users.builder()
                .email("gorb6593@naver.com")
                .password("1234")
                .name("이해규")
                .build();

        userRepository.save(users);

        Signup signup = Signup.builder()
                .email("gorb6593@naver.com")
                .password("1234")
                .name("해규")
                .build();

        //expected
        assertThrows(AlreadyExistsEmailException.class, () -> authService.signup(signup));
//        Users users = userRepository.findAll().iterator().next();
//        assertEquals("gorb6593@naver.com", users.getEmail());
//        assertEquals("1234", users.getPassword());
//        assertEquals("해규", users.getName());

    }

}