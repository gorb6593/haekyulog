package com.haekyulog.haekyulog.repository;

import com.haekyulog.haekyulog.domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {

    Optional<Users> findByEmailAndPassword(String email, String password);
}
