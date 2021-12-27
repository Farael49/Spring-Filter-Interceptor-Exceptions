package com.example.filter.config.interceptor;

import com.example.filter.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomInterceptor implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        generateException(request);
        return true;
    }

    private void generateException(HttpServletRequest request) throws Exception {
        if (request.getRequestURI().contains("interceptor")) {
            LOGGER.error("Within Interceptor");
            throw new CustomException("Exception within Interceptor");
        }
    }
}
