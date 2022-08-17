package com.foorun.unieat.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foorun.unieat.constant.ResponseCode;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.exception.UniEatRuntimeException;
import com.foorun.unieat.exception.UniEatUnAuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationExceptionFilter  extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);

        }catch (UniEatUnAuthorizationException e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            ApiResponse errorDetail = ApiResponse.error(e.getResponseCode());
            objectMapper.writeValue(response.getWriter(), errorDetail);
        }
    }
}
