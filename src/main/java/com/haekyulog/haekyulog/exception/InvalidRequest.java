package com.haekyulog.haekyulog.exception;

/**
 * status -> 400
 */

public class InvalidRequest extends HaekyulogException{
    public InvalidRequest(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }


}
