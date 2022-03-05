package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.constant.EmailVerifyType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSendVerifyEmailPayload {
    @ApiModelProperty(notes = "사용자 이메일", example = "cha2.hoon@gmail.com")
    private String email;

    @ApiModelProperty(notes = "인증 타입", example = "SIGN_UP")
    private EmailVerifyType verifyType;
}
