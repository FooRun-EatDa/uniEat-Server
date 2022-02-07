package com.foorun.unieat.jwt;

import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.util.JwtProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest

public class jwtProviderTest {

    @Autowired
    JwtProvider jwtProvider;

    @Test
    public void jwtProviderTest(){

        String token = jwtProvider.createToken("hyun", Role.USER);
        assertThat(token).isNotEmpty();

        assertThat(jwtProvider.IsValidateToken(token)).isTrue();

    }
}
