package com.example.logging.model.builder;

import com.example.logging.model.ErrorResponse;

import java.util.Date;

public final class ErrorResponseBuilder {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private ErrorResponseBuilder() {
    }

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public ErrorResponseBuilder timestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ErrorResponseBuilder status(Integer status) {
        this.status = status;
        return this;
    }

    public ErrorResponseBuilder error(String error) {
        this.error = error;
        return this;
    }

    public ErrorResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponseBuilder path(String path) {
        this.path = path;
        return this;
    }

    public ErrorResponse build() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(timestamp);
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);
        errorResponse.setPath(path);
        return errorResponse;
    }
}
