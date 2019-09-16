package com.example.logging.interceptor;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;

public interface InterceptorRequestWrapper extends HttpServletRequest {
    String getBody();
    void setBody(String body);
    BufferedReader getReader();
    ServletInputStream getInputStream();
}
