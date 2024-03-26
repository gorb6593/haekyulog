package com.haekyulog.haekyulog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Session> sessions = new ArrayList<>();

    public void addSession() {
        sessions.add(Session.builder()
                .users(this)
                .build());
    }
}
