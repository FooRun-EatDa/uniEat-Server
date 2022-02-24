package com.foorun.unieat.util;

import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.domain.common.jwt.JwtToken;
import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key key;
    private final long tokenExpiryTime;
    private final long refreshTokenExpiryTime;

    @Autowired
    public JwtProvider(@Value("${jwt.secret-key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.tokenExpiryTime = 30 * 60 * 1000L; //  30 minutes
        this.refreshTokenExpiryTime = tokenExpiryTime * 2 * 24 * 7; // 7 days
    }

    /**
     * JWT Token 생성
     * @param email 사용자 이메일 - MemberJpo.email
     * @param nickname 사용자 닉네임 - MemberJpo.nickname
     * @param role 권한 - MemberJpo.role
     * @return token
     */
    public String createToken(String email, String nickname, Role role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        claims.put("email", email);
        claims.put("nickname", nickname);
        Date now = new Date();
        return String.format("Bearer %s", Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpiryTime))
                .signWith(key)
                .compact());
    }

    /**
     * RefreshToken 생성
     * @return refreshToken
     */
    public String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenExpiryTime))
                .signWith(key)
                .compact();
    }

    /**
     * Token, RefreshToken 함께 생성
     * @param email 사용자 이메일 - MemberJpo.email
     * @param nickname 사용자 닉네임 - MemberJpo.nickname
     * @param role 권한 - MemberJpo.role
     * @return {@link JwtToken}
     */
    public JwtToken createTokens(String email, String nickname, Role role) {
        return JwtToken.of(createToken(email, nickname, role), createRefreshToken());
    }

    /**
     * 토큰으로부터 유저 닉네임 추출
     * @param token JWT 토큰
     * @return 토큰에 저장된 유저 닉네임
     */
    public String getMemberNickname(String token) {
        return getClaimProperty(token, "nickname", String.class);
    }

    /**
     * 토큰으로부터 유저 권한 등급
     * @param token JWT 토큰
     * @return 토큰에 저장된 유저 권한
     */
    public Role getMemberRole(String token) {
        return Role.valueOf(getClaimProperty(token, "role", String.class));
    }

    /**
     * 토큰으로부터 유저 닉네임 추출
     * @param token JWT 토큰
     * @return 토큰에 저장된 유저 닉네임
     */
    public String getMemberEmail(String token) {
        return getClaimProperty(token, "email", String.class);
    }

    private <T> T getClaimProperty(String token, String property, Class<T> clazz) {
        Jws<Claims> claims = getParsedClaimsJws(token);
        return claims.getBody().get(property, clazz);
    }

    private Jws<Claims> getParsedClaimsJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    /**
     * 토큰 유효성 검사
     * @param token Jwt Token
     * @return 유효한지 여부에 대한 boolean 값
     */
    public boolean isExpiredToken(String token) {
        try {
            Jws<Claims> claims = getParsedClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .before(new Date()); // 유효기간 검사
        } catch (Exception e) { // 서명 불일치
            return true;
        }
    }

    /**
     * 헤더에서 토큰을 뽑아옴
     * Authorization : Bearer {token} 형태
     * @return Authorization 값에서 "Bearer " 제거한 순수 Token
     */
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(JwtConstant.HEADER_NAME);
        if (token != null && token.contains(JwtConstant.TOKEN_PREFIX)) {
            token = token.replaceFirst(JwtConstant.TOKEN_PREFIX + " ","");
            return token;
        }
        throw new UniEatForbiddenException();
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        if (existsRefreshToken(request)) {
            return request.getHeader(JwtConstant.HEADER_NAME_REFRESH_TOKEN);
        }
        throw new UniEatNotFoundException();
    }

    public boolean existsToken(HttpServletRequest request) {
        return existsToken(request, JwtConstant.HEADER_NAME);
    }

    public boolean existsRefreshToken(HttpServletRequest request) {
        return existsToken(request, JwtConstant.HEADER_NAME_REFRESH_TOKEN);
    }

    private boolean existsToken(HttpServletRequest request, String name) {
        return StringUtils.hasText(request.getHeader(name));
    }
}
