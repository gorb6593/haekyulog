package com.haekyulog.haekyulog.config;

import com.haekyulog.haekyulog.domain.Users;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserPrincipal extends User {

    private final Long userId;

    public UserPrincipal(Users users) {
        super(users.getEmail(), users.getPassword(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_USER")
                        ));
        this.userId = users.getId();
    }

}
