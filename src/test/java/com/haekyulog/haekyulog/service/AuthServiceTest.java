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

import static org.junit.jupiter.api.Assertions.*;

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
        assertNotNull(users.getPassword());
        assertNotEquals("1234", users.getPassword());
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
    }

//    @Test
//    @DisplayName("로그인 성공")
//    void test3() {
//        //given
//        PasswordEncoder encoder = new PasswordEncoder();
//        String encrypt = encoder.encrypt("1234");
//
//        Users users = Users.builder()
//                .email("gorb6593@naver.com")
//                .password(encrypt)
//                .name("이해규")
//                .build();
//
//        userRepository.save(users);
//
//        //when
//        Login login = Login.builder()
//                .email("gorb6593@naver.com")
//                .password("1234")
//                .build();
//
//        Long signin = authService.signin(login);
//
//        //then
//        assertNotNull(signin);
//    }
//
//    @Test
//    @DisplayName("로그인 비밀번호 틀림")
//    void test4() {
//        //given
//        PasswordEncoder encoder = new PasswordEncoder();
//        String encrypt = encoder.encrypt("1234");
//
//        Users users = Users.builder()
//                .email("gorb6593@naver.com")
//                .password(encrypt)
//                .name("이해규")
//                .build();
//
//        userRepository.save(users);
//
//        //when
//        Login login = Login.builder()
//                .email("gorb6593@naver.com")
//                .password("12345")
//                .build();
//
//        //expected
//        assertThrows(InvalidSigninInformation.class, () -> authService.signin(login));
//
//
//
//
//    }

}