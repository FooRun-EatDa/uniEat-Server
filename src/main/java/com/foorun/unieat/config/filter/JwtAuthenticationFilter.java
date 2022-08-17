package com.foorun.unieat.config.filter;

import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.domain.common.jwt.JwtToken;
import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.exception.UniEatUnAuthorizationException;
import com.foorun.unieat.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.foorun.unieat.constant.JwtConstant.AUTH_MEMBER_PREFIX;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(existsToken(request, AUTH_MEMBER_PREFIX)){
            log.info("토큰 존재");
            String token = jwtProvider.resolveMemberIdToken(request);
            log.info("MemberID is == {}",token);
            if(!existsAuthentication()){
                log.info("setAuth");
                setAuthenticationByMemberId(token);
            }
        }

        filterChain.doFilter(request, response);
        /*
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
        */


    }

    //추후 사용 jwt토큰용
    private void setAuthentication(String token) {
        SecurityContextHolder.getContext().setAuthentication(getAuthenticationToken(token));
    }

    //X-MEMBER-ID용
    private void setAuthenticationByMemberId(String token){
        SecurityContextHolder.getContext().setAuthentication(getAuthenticationByXMemberId(token));
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


    //추후 사용 예정
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


    private UsernamePasswordAuthenticationToken getAuthenticationByXMemberId(String token){
      try{
       MemberJpo memberJpo = memberRepository.findById(Long.parseLong(token)).orElseThrow(UniEatNotFoundException::new);
       UserDetails userDetails = MemberUserDetails.of(memberJpo);
       return new UsernamePasswordAuthenticationToken(
               userDetails,
               null,
                Collections.singletonList(new SimpleGrantedAuthority(Role.USER.getKey()))
       );
      }catch (UniEatNotFoundException e){ //멤버 못찾으면 인증 못된걸로 처리
          throw new UniEatUnAuthorizationException();
      }
    }

    private boolean existsToken(HttpServletRequest request, String name) {
        return StringUtils.hasText(request.getHeader(name));
    }
}
