package com.foorun.unieat.domain.member.dto.setting;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.Validatable;
import com.foorun.unieat.exception.badrequest.UniEatInvalidParameterException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.foorun.unieat.util.RegexUtil.isMatchMemberPassword;

@Getter
@Setter
@ToString
public class MemberSettingPasswordPayload implements JsonSerializable, Validatable {
    @ApiModelProperty(notes = "기존 비밀번호", example = "chaehun")
    private String password;

    @ApiModelProperty(notes = "변경할 비밀번호", example = "chaehun!@#$")
    private String newPassword;

    @ApiModelProperty(notes = "변경할 비밀번호 확인", example = "chaehun!@#$")
    private String newPasswordRe;

    @Override
    public void validate() {
        if (!isMatchMemberPassword(newPassword)) {
            throw new UniEatInvalidParameterException("newPassword");
        }
        if (!newPassword.equals(newPasswordRe)) {
            throw new UniEatInvalidParameterException("newPassword or newPasswordRe");
        }
    }
}
