package com.haekyulog.haekyulog.controller;

import com.haekyulog.haekyulog.requesst.Login;
import com.haekyulog.haekyulog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    //private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/auth/login")
    public void login(@RequestBody Login login) {
        //json 아이디/비밀번호
         log.info(">>> login : {}" , login);
        //db에서 조회
//        Users users = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
//                .orElseThrow(InvalidSigninInformation::new);
        authService.signin(login);
        //토큰을 응답

    }
}
