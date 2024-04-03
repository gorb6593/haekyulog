package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Session;
import com.haekyulog.haekyulog.domain.Users;
import com.haekyulog.haekyulog.repository.InvalidSigninInformation;
import com.haekyulog.haekyulog.repository.UserRepository;
import com.haekyulog.haekyulog.requesst.Login;
import com.haekyulog.haekyulog.requesst.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public Long signin(Login login) {
        Users users = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);

        Session session = users.addSession();
        return users.getId();
    }

    public void signup(Signup signup) {
        Users users = Users.builder()
                .name(signup.getName())
                .password(signup.getPassword())
                .email(signup.getEmail())
                .build();
        userRepository.save(users);
    }
}
