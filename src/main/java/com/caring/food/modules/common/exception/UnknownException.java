package com.caring.food.modules.common.exception;

public class UnknownException extends RuntimeException {
    public UnknownException() {
        super();
    }
    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }
    public UnknownException(String message) {
        super(message);
    }
    public UnknownException(Throwable cause) {
        super(cause);
    }
}
