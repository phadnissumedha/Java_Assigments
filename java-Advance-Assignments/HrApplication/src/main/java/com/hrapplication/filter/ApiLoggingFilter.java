package com.hrapplication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiLoggingFilter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);

        String userId = request.getHeader("user-id");
        String clientReqId = request.getHeader("client_reqid");

        MDC.put("user", userId != null ? userId : "anonymous");
        MDC.put("api", request.getMethod() + " " + request.getRequestURI());
        MDC.put("client_reqid", clientReqId != null ? clientReqId : "");

        try {
            filterChain.doFilter(wrappedRequest, response);

            MDC.put("status", String.valueOf(response.getStatus()));
            MDC.put("error_code", "");
            MDC.put("error_message", "");

        } catch (Exception ex) {

            MDC.put("status", "500");
            MDC.put("error_code", "INTERNAL_ERROR");
            MDC.put("error_message", ex.getMessage());
            throw ex;

        } finally {
            MDC.put("req", formatRequest(wrappedRequest.getBody()));
            log.info("API_REQUEST");
            MDC.clear();
        }
    }

    private String formatRequest(String body) {
        if (body == null || body.isBlank()) {
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(
                    objectMapper.readTree(body));
        } catch (Exception e) {
            return body;
        }
    }
}