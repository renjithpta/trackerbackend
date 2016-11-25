package com.ust.userwebapp.security.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ust.userwebapp.web.exception.bean.ApiError;



@Component("restAuthenticationEntryPoint")
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public final void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
     
        response.setHeader("content-type", "application/json");
        
        response.getWriter().println(new ApiError(401, "Not authorized", "Not Authorized"));
    }

}
