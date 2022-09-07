package com.caring.food.modules.common.exception;

public class ApiErrorException extends RuntimeException {
    public ApiErrorException() {
        super();
    }
    public ApiErrorException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApiErrorException(String message) {
        super(message);
    }
    public ApiErrorException(Throwable cause) {
        super(cause);
    }
}
