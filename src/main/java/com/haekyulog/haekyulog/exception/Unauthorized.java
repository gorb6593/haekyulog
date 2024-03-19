package com.haekyulog.haekyulog.exception;

/**
 * status -> 401
 */
public class Unauthorized extends HaekyulogException{

    private static final String MESSAGE = "인증이 필요합니다.";

    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode()  {
        return 401;
    }
}
