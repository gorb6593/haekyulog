package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Users;
import com.haekyulog.haekyulog.repository.InvalidSigninInformation;
import com.haekyulog.haekyulog.repository.UserRepository;
import com.haekyulog.haekyulog.requesst.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void signin(Login login) {
        Users users = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);
    }
}
