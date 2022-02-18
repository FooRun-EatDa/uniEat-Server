package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.Validatable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignIn implements JsonSerializable, Validatable {
    @ApiModelProperty(notes = "이메일", example = "cha2.hoon@gmail.com")
    private String email;

    @ApiModelProperty(notes = "비밀번호", example = "1")
    private String password;

    @Override
    public void validate() {

    }
}
