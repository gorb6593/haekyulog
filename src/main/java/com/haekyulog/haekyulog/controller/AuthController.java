package com.haekyulog.haekyulog.controller;

import com.haekyulog.haekyulog.requesst.Login;
import com.haekyulog.haekyulog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    //private final UserRepository userRepository;
    private final AuthService authService;

    //토큰 버전
//    @PostMapping("/auth/login")
//    public SessionResponse login(@RequestBody Login login) {
//        //json 아이디/비밀번호
//         log.info(">>> login : {}" , login);
//        //db에서 조회
////        Users users = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
////                .orElseThrow(InvalidSigninInformation::new);
//        String accessToken = authService.signin(login);
//        return new SessionResponse(accessToken);
//        //토큰을 응답
//
//    }

    //쿠키버전
    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody Login login) {
        String accessToken = authService.signin(login);
        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
                .domain("localhost") //todo 서버 환경에 따른 분리 필요
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict")
                .build();

        log.info(">>>>> cookie = {} ", cookie);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
