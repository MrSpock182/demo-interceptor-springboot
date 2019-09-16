package com.example.logging.interceptor;

import javax.servlet.http.HttpServletRequest;

public interface InterceptorFactor {
    InterceptorRequestWrapper getInstance(HttpServletRequest request);
}
