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

@DisplayName("사용자 회원가입 닉네임 중복여부")
public class MemberSignUpCheckNicknameTests extends MemberSignUpTests {
    @DisplayName("닉네임 중복 확인 - 중복이 아닐 경우")
    @ParameterizedTest
    @ValueSource(strings = "chaehoon")
    void signUpCheckNicknameWillPass(String nickname) throws Exception {
        when(memberSignUpService.isDuplicateNickname(anyString())).thenReturn(false);

        mockMvc.perform(get("/member/sign-up/check-nickname")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("nickname", nickname)
                        .headers(HttpHeaders.EMPTY))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"))
                .andExpect(jsonPath("data").value(false));
    }

    @DisplayName("닉네임 중복 확인 - 중복일 경우")
    @ParameterizedTest
    @ValueSource(strings = "chaehoon")
    void signUpCheckNicknameWillFail(String nickname) throws Exception {
        when(memberSignUpService.isDuplicateNickname(anyString())).thenReturn(true);

        mockMvc.perform(get("/member/sign-up/check-nickname")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("nickname", nickname)
                        .headers(HttpHeaders.EMPTY))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"))
                .andExpect(jsonPath("data").value(true));
    }
}
