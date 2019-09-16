package com.example.logging.api.error;

import com.example.logging.component.StackLogger;
import com.example.logging.exception.InternalServerErrorException;
import com.example.logging.model.ErrorResponse;
import com.example.logging.model.builder.ErrorResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private StackLogger stackLogger;

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleInternalServerError(Exception exception, HttpServletRequest request) {
        stackLogger.logger(exception, request);

        return ErrorResponseBuilder.builder()
                .timestamp(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("INTERNAL SERVER ERROR")
                .message(exception.getMessage())
                .path(request.getServletPath())
                .build();
    }

}
