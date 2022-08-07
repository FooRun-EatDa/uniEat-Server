package com.foorun.unieat.domain.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVerifyEmailPayload {
    @ApiModelProperty(notes = "사용자 이메일", example = "cha2.hoon@gmail.com", required = true)
    private String email;

    @ApiModelProperty(notes = "사용자가 입력한 인증코드", example = "1234567890", required = true)
    private String verificationCode;
}
