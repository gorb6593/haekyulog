package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Users;
import com.haekyulog.haekyulog.exception.AlreadyExistsEmailException;
import com.haekyulog.haekyulog.repository.UserRepository;
import com.haekyulog.haekyulog.requesst.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    @Transactional
//    public Long signin(Login login) {
////        Users users = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
////                .orElseThrow(InvalidSigninInformation::new);
////
////        Session session = users.addSession();
//
//        Users users = userRepository.findByEmail(login.getEmail())
//                .orElseThrow(InvalidSigninInformation::new);
//
//        PasswordEncoder encoder = new PasswordEncoder();
//
//        var matches = encoder.matches(login.getPassword(), users.getPassword());
//
//        if (!matches) {
//            throw new InvalidSigninInformation();
//        }
//
//        return users.getId();
//    }

    public void signup(Signup signup) {

        Optional<Users> byEmail = userRepository.findByEmail(signup.getEmail());

        if (byEmail.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

//        PasswordEncoder encoder = new PasswordEncoder();
//
//        String encodedPassword = encoder.encrypt(signup.getPassword());
        String encodedPassword = passwordEncoder.encode(signup.getPassword());

        Users users = Users.builder()
                .name(signup.getName())
                .password(encodedPassword)
                .email(signup.getEmail())
                .build();
        userRepository.save(users);
    }
}
