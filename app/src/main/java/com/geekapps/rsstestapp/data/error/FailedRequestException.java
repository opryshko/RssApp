package com.geekapps.rsstestapp.data.error;

public class FailedRequestException extends RuntimeException {

    public FailedRequestException() {
    }

    public FailedRequestException(String message) {
        super(message);
    }

    public FailedRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedRequestException(Throwable cause) {
        super(cause);
    }

}
