package com.foorun.unieat.domain.member.dto.setting;

import com.foorun.unieat.domain.JsonSerializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class MemberSettingNicknamePayload implements JsonSerializable {
    @NotEmpty
    @ApiModelProperty(notes = "변경할 닉네임", example = "채훈채훈채훈")
    private String nickname;
}
