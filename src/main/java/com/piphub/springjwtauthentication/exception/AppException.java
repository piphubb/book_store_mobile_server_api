package com.piphub.springjwtauthentication.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AppException extends Exception{
    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    public AppException() {
        super();
    }

    public AppException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = status;
    }

    public AppException(HttpStatus status, String errorCode, String message) {
        this.errorCode = errorCode;
        this.httpStatus = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "AppException{" +
                "httpStatus=" + httpStatus +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
