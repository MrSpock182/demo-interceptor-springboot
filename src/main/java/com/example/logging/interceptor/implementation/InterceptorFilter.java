package com.example.logging.interceptor.implementation;

import com.example.logging.interceptor.InterceptorFactor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
class InterceptorFilter implements Filter {

    private FilterConfig filterConfig;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private InterceptorFactor interceptorFactor;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

//        request = new InterceptorRequestWrapperImpl((HttpServletRequest) request);

        request = interceptorFactor.getInstance((HttpServletRequest) request);

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfiguration) {
        this.filterConfig = filterConfiguration;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
