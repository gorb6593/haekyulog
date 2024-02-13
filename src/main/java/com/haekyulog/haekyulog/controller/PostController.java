package com.haekyulog.haekyulog.controller;

import com.haekyulog.haekyulog.requesst.PostCreate;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PostController {

    // ssr => jsp, thymeleaf, mustache, freemarker
    // spa =>
    //   vue   => vue + ssr => nuxt.js
    //   react => react + ssr => next.js
    @GetMapping("/posts")
    public String get() {
        return "Hello world";
    }

    // Http Method
    // Get, Post, Put, Patch, Delete, Options, Head, Trace, connect
    // 글 등록
    // post method

    @PostMapping("/posts")
    public String post(@RequestBody @Valid PostCreate postCreate) {
        log.info("postCreate = {}" , postCreate.toString());
        return "Hello world";
    }
}
