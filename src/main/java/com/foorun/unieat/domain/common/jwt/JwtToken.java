package com.foorun.unieat.domain.common.jwt;

import com.foorun.unieat.constant.JwtConstant;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class JwtToken {
    private String token;
    private String refreshToken;

    /**
     * static factory method
     */
    public static JwtToken of(String token, String refreshToken) {
        return new JwtToken(token, refreshToken);
    }

    public MultiValueMap<String, String> asMultiValueMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(JwtConstant.HEADER_NAME, token);
        map.add(JwtConstant.HEADER_NAME_REFRESH_TOKEN, refreshToken);
        return map;
    }

    public HttpHeaders asHeaders() {
        return new HttpHeaders(asMultiValueMap());
    }
}
