package com.foorun.unieat.config.filter;

import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.domain.common.jwt.JwtToken;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //  Access Token 이 존재할 경우
        if (jwtProvider.existsToken(request)) {
            String token = jwtProvider.resolveToken(request);
            if (!jwtProvider.isExpiredToken(token) && !existsAuthentication()) {
                setAuthentication(token);
            } else {
                //  Refresh Token 이 존재할 경우
                if (jwtProvider.existsRefreshToken(request)) {
                    String refreshToken = jwtProvider.resolveRefreshToken(request);
                    if (!jwtProvider.isExpiredToken(refreshToken)) {
                        reIssueToken(refreshToken, response);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String token) {
        SecurityContextHolder.getContext().setAuthentication(getAuthenticationToken(token));
    }

    /**
     * Refresh Token 이 존재할 경우 AccessToken 재발급
     */
    private void reIssueToken(String refreshToken, HttpServletResponse response) {
        Long memberId = jwtProvider.getMemberId(refreshToken);
        JwtToken jwtToken = jwtProvider.createTokens(memberRepository
                .findById(memberId)
                .orElseThrow(UniEatNotFoundException::new)
                .asUserDetails());
        setAuthentication(jwtToken.getTokenWithoutPrefix());
        response.setHeader(JwtConstant.HEADER_NAME, jwtToken.getToken());
        response.setHeader(JwtConstant.HEADER_NAME_REFRESH_TOKEN, jwtToken.getRefreshToken());
    }

    private boolean existsAuthentication() {
        return nonNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        UserDetails userDetails = MemberUserDetails.of(
                jwtProvider.getMemberId(token),
                jwtProvider.getMemberEmail(token),
                jwtProvider.getMemberNickname(token),
                jwtProvider.getMemberRole(token));
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
    }
}
