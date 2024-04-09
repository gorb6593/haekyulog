package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Session;
import com.haekyulog.haekyulog.domain.Users;
import com.haekyulog.haekyulog.exception.AlreadyExistsEmailException;
import com.haekyulog.haekyulog.repository.InvalidSigninInformation;
import com.haekyulog.haekyulog.repository.UserRepository;
import com.haekyulog.haekyulog.requesst.Login;
import com.haekyulog.haekyulog.requesst.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

        Optional<Users> byEmail = userRepository.findByEmail(signup.getEmail());

        if (byEmail.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(16, 8, 1, 32, 64);

        String encodedPassword = encoder.encode(signup.getPassword());

        Users users = Users.builder()
                .name(signup.getName())
                .password(encodedPassword)
                .email(signup.getEmail())
                .build();
        userRepository.save(users);
    }
}
