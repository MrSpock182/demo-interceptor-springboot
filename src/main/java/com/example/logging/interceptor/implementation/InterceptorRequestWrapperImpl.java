package com.example.logging.interceptor.implementation;

import com.example.logging.exception.InternalServerErrorException;
import com.example.logging.interceptor.InterceptorRequestWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

class InterceptorRequestWrapperImpl extends HttpServletRequestWrapper implements InterceptorRequestWrapper {
    private String body;

    public InterceptorRequestWrapperImpl(HttpServletRequest request) {
        super(request);
        body = getBody(request).toString();
    }

    private StringBuilder builder(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = buffer.read(charBuffer)) > 0) {
                builder.append(charBuffer, 0, bytesRead);
            }
        } catch (Exception ex) {
            throw new InternalServerErrorException("Request error: " + ex.getMessage());
        }

        return builder;
    }

    private StringBuilder getBody(HttpServletRequest request) {
        try {
            InputStream inputStream = request.getInputStream();

            if (inputStream != null) {
                return builder(inputStream);
            }

            return new StringBuilder();
        } catch (IOException ex) {
            throw new InternalServerErrorException("Request error: " + ex.getMessage());
        }
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new InterceptorServletStream(byteArrayInputStream);
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

}