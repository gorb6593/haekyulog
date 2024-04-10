package com.haekyulog.haekyulog.controller;

import com.haekyulog.haekyulog.config.AppConfig;
import com.haekyulog.haekyulog.requesst.Signup;
import com.haekyulog.haekyulog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    //private final UserRepository userRepository;
    private final AuthService authService;
    private final AppConfig appConfig;

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
//    @PostMapping("/auth/login")
//    public ResponseEntity<Object> login(@RequestBody Login login) {
//        String accessToken = authService.signin(login);
//        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
//                .domain("localhost") //todo 서버 환경에 따른 분리 필요
//                .httpOnly(true)
//                .secure(false)
//                .maxAge(Duration.ofDays(30))
//                .sameSite("Strict")
//                .build();
//
//        log.info(">>>>> cookie = {} ", cookie);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .build();
//    }

    //JWT
//    @PostMapping("/auth/login")
//    public SessionResponse login(@RequestBody Login login) {
//        Long userId = authService.signin(login);
//
//        //SecretKey key = Jwts.SIG.HS256.key().build();
//        //SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));
//        //Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        //byte[] encodedKey = key.getEncoded();
//        //String strKey = Base64.getEncoder().encodeToString(encodedKey);
//
//        //SecretKey key2 = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));
//
//        SecretKey key = Keys.hmacShaKeyFor(appConfig.getJwtKey());
//
//        String jws = Jwts.builder()
//                .subject(String.valueOf(userId))
//                .signWith(key)
//                .setIssuedAt(new Date())
//                .compact();
//
//        return new SessionResponse(jws);
//    }

    @PostMapping("/auth/signup")
    public void signup(@RequestBody Signup signup) {
        authService.signup(signup);
    }

    @GetMapping("/auth/login")
    public String login () {
        return "로그인페이지입니다.";
    }
}
