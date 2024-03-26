package com.haekyulog.haekyulog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;

    @ManyToOne
    private Users users;

    @Builder
    public Session(Users users) {
        this.accessToken = randomUUID().toString();
        this.users = users;
    }
}

