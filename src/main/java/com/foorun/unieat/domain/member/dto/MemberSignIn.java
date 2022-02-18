package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.domain.JsonSerializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class MemberSignIn implements JsonSerializable {
    @ApiModelProperty(notes = "이메일", example = "cha2.hoon@gmail.com")
    private final String email;

    @ApiModelProperty(notes = "비밀번호", example = "1")
    private final String password;
}
