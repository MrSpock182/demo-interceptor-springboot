package com.example.logging.interceptor.implementation;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

class InterceptorServletStream extends ServletInputStream {

    private ByteArrayInputStream byteArrayInputStream;

    InterceptorServletStream(ByteArrayInputStream byteArrayInputStream) {
        this.byteArrayInputStream = byteArrayInputStream;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        // Do nothing because this method is required
    }

    @Override
    public int read() throws IOException {
        return byteArrayInputStream.read();
    }
}
