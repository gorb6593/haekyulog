package com.haekyulog.haekyulog.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 * "code"    : "400"
 * "message" : "잘못된 요청입니다."
 * "validation" : {
 * "title"   : "값을 주십쇼"
 * "content" : "얘도 값을 주십쇼"
 * ...
 * }
 * }
 */
@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation = new HashMap<>();

    public void addValidation(String filedName, String message) {
        this.validation.put(filedName, message);
    }

    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
