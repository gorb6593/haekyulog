package com.haekyulog.haekyulog.controller;

import com.haekyulog.haekyulog.requesst.PostCreate;
import com.haekyulog.haekyulog.response.PostResponse;
import com.haekyulog.haekyulog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class PostController {

    // ssr => jsp, thymeleaf, mustache, freemarker
    // spa =>
    //   vue   => vue + ssr => nuxt.js
    //   react => react + ssr => next.js
    private final PostService postService;


//    @GetMapping("/posts")
//    public String get() {
//        return "Hello world";
//    }

    // Http Method
    // Get, Post, Put, Patch, Delete, Options, Head, Trace, connect
    // 글 등록
    // post method

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate postCreate) {
        postService.write(postCreate);
    }

    /**
     * /posts -> 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    // 조회 API
    // 단건 조회
    // 다건 조회
    @GetMapping("/posts")
    public List<PostResponse> getList(Pageable pageable) {
        return postService.getList(pageable);
    }
}
