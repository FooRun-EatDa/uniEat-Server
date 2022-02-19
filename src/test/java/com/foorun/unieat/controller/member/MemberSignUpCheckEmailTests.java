package com.foorun.unieat.controller.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("사용자 회원가입 이메일 중복여부")
public class MemberSignUpCheckEmailTests extends MemberSignUpTests {
    @DisplayName("이메일 중복 확인 - 중복이 아닐 경우")
    @ParameterizedTest
    @ValueSource(strings = "xxxxxxxx@xxxxxx.xx.xx")
    void signUpCheckEmailWillPass(String email) throws Exception {
        when(memberRepository.existsByEmail(anyString())).thenReturn(false);

        mockMvc.perform(get("/member/sign-up/check-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("email", email)
                        .headers(HttpHeaders.EMPTY))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"))
                .andExpect(jsonPath("data").value(false));
    }

    @DisplayName("이메일 중복 확인 - 중복일 경우")
    @ParameterizedTest
    @ValueSource(strings = "xxxxxxxx@xxxxxx.xx.xx")
    void signUpCheckEmailWillFail(String email) throws Exception {
        when(memberRepository.existsByEmail(anyString())).thenReturn(true);

        mockMvc.perform(get("/member/sign-up/check-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("email", email)
                        .headers(HttpHeaders.EMPTY))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"))
                .andExpect(jsonPath("data").value(true));
    }
}
