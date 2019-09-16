package com.example.logging.component;

import javax.servlet.http.HttpServletRequest;

public interface StackLogger {
    void logger(Exception exception, HttpServletRequest request);
}
