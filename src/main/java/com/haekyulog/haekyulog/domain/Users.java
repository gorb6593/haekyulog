package com.haekyulog.haekyulog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
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

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
//    private List<Session> sessions = new ArrayList<>();

//    public Session addSession() {
//        Session session = Session.builder()
//                .users(this)
//                .build();
//        sessions.add(session);
//
//        return session;
//    }

    @Builder
    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }
}
