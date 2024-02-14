package com.haekyulog.haekyulog.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
//@RequiredArgsConstructor
@Builder
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;

}
