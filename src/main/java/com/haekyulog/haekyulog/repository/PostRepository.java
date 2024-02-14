package com.haekyulog.haekyulog.repository;

import com.haekyulog.haekyulog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
