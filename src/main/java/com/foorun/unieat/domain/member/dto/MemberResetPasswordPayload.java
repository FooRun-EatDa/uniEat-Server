package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.domain.JsonSerializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberResetPasswordPayload implements JsonSerializable {
    @ApiModelProperty(notes = "사용자 이메일", example = "cha2.hoon@gmail.com")
    private String email;

    @ApiModelProperty(notes = "사용자가 입력한 비밀번호", example = "chaehun")
    private String password;
}
