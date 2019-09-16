package com.example.logging.component.implementation;

import com.example.logging.component.StackLogger;
import com.example.logging.interceptor.InterceptorFactor;
import com.example.logging.interceptor.InterceptorRequestWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class StackLoggerImpl implements StackLogger {

    private static Logger logger = LogManager.getLogger(StackLoggerImpl.class);

    @Autowired
    private InterceptorFactor interceptorFactor;

    public void logger(Exception exception, HttpServletRequest request) {
        try {
            InterceptorRequestWrapper interceptor = interceptorFactor.getInstance(request);
            StringBuilder sb = new StringBuilder();
            sb.append("---------------- Application Error ----------------" + '\n')
                    .append(String.format("Request: %s", interceptor.getBody()) + '\n')
                    .append(String.format("Path: %s", request.getServletPath()) + '\n')
                    .append(String.format("Message Error: %s", exception.getMessage()) + '\n')
                    .append(String.format("Cause: %s", exception.getCause()) + '\n')
                    .append(String.format("Processors (cores): %s",
                            Runtime.getRuntime().availableProcessors()) + '\n')
                    .append(String.format("Free memory (bytes): %s",
                            Runtime.getRuntime().freeMemory()) + '\n')
                    .append("---------------- Stack Trace ----------------" + '\n');

            for (StackTraceElement stack : exception.getStackTrace()) {
                sb.append(String.format("Class: %s", stack.getClassName()) + '\n')
                        .append(String.format("Method: %s", stack.getMethodName()) + '\n')
                        .append(String.format("File: %s", stack.getFileName()) + '\n')
                        .append(String.format("Line Error: %d", stack.getLineNumber()) + '\n');
            }

            logger.error(sb.toString());
        } catch (Exception ex) {
            logger.fatal(String.format("Erro ao criar log: %s", ex.getMessage()));
        }
    }

}
