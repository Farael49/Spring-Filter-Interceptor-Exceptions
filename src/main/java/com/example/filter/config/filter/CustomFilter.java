package com.example.filter.config.filter;

import com.example.filter.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.I_AM_A_TEAPOT;

@Configuration
public class CustomFilter extends GenericFilterBean {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            generateException(request);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            setExceptionResponse(response, e);
        }
    }

    private void generateException(HttpServletRequest request) throws Exception {
        if (request.getRequestURI().contains("filter")) {
            LOGGER.error("Within Filter");
            throw new CustomException("Exception within Filter");
        }
    }

    private void setExceptionResponse(HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(I_AM_A_TEAPOT.value());
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.getWriter().write(e.toString());
    }
}