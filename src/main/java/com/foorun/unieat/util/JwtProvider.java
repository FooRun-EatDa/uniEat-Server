package com.foorun.unieat.util;

import com.foorun.unieat.domain.member.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private String JWT_HEADER_STRING="Authorization";
    private String JWT_TOKEN_PREFIX="Bearer";

    private String secretKey;
    private Key key;

    @Autowired
    public JwtProvider(@Value("${jwt.secret-key}") String secretKey){
        this.secretKey = secretKey;
        this.key= Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }



    private long tokenValidTime = 30 * 60 * 1000L;

    public String createToken(String userNickname, Role role){
        Claims claims = Jwts.claims().setSubject(userNickname);
        claims.put("roles",role.getKey());
        claims.put("userNickname",userNickname);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(key)
                .compact();


    }

    /**
     * 토큰으로부터 유저 닉네임 추출
     * @param token
     * @return 토큰에 저장된 유저 닉네임
     */
    public String getUserNickname(String token){
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(token);

        return claims.getBody().get("userNickname",String.class);
    }


    /**
     * 토큰 유효성 검사
     * @param token
     * @return 유효한지 여부에 대한 boolean 값
     */
    public boolean IsValidateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date()); // 유효기간 검사
        }catch (Exception e){ // 서명 불일치
            return false;
        }
    }


    /**
     * 헤더에서 토큰을 뽑아옴
     * Authorization : Bearer {token} 형태
     * @param request
     * @return
     */
    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader(JWT_HEADER_STRING);
        if(token != null && token.contains(JWT_TOKEN_PREFIX)){
            token = token.replaceFirst(JWT_TOKEN_PREFIX+" ","");
            return token;
        }
        else{
            //토큰 누락, 예외 처리 필요
            return null;

        }

    }

}
