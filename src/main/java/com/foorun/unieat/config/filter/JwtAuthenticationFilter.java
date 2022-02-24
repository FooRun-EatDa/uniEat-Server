package com.foorun.unieat.config.filter;

import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (jwtProvider.existsToken(request)) {
            String token = jwtProvider.resolveToken(request);
            if (!jwtProvider.isExpiredToken(token) && !existsAuthentication()) {
                SecurityContextHolder.getContext().setAuthentication(getAuthenticationToken(token));
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean existsAuthentication() {
        return nonNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        UserDetails userDetails = MemberUserDetails.of(
                jwtProvider.getMemberEmail(token),
                jwtProvider.getMemberNickname(token),
                jwtProvider.getMemberRole(token));
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
    }
}
