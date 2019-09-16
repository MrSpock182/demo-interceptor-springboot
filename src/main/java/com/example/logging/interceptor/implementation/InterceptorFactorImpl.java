package com.example.logging.interceptor.implementation;

import com.example.logging.interceptor.InterceptorFactor;
import com.example.logging.interceptor.InterceptorRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class InterceptorFactorImpl implements InterceptorFactor {

    @Override
    public InterceptorRequestWrapper getInstance(HttpServletRequest request) {
        return new InterceptorRequestWrapperImpl(request);
    }
}
