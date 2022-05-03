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
public class MemberSettingProfilePayload implements JsonSerializable {
    @NotEmpty
    @ApiModelProperty(notes = "프로필 이미지(파일) 고유 ID", example = "5073596e-ac44-46d5-9a89-c29610df4ebd")
    private String fileId;
}
