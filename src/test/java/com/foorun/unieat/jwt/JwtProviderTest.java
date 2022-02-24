package com.foorun.unieat.jwt;

import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.util.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class JwtProviderTest {

    @Autowired
    JwtProvider jwtProvider;

    @Test
    public void jwtProviderTest(){
        String token = jwtProvider.createToken("aaaa@aaaa.aa.aa", "hyun", Role.USER);
        assertThat(token).isNotEmpty();
        assertThat(jwtProvider.isExpiredToken(token)).isTrue();
    }
}
