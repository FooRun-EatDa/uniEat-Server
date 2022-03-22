package com.foorun.unieat.domain.member.dto.setting;

import com.foorun.unieat.domain.JsonSerializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSettingNotificationPayload implements JsonSerializable {
    @ApiModelProperty(notes = "활성/비활성 여부(true : 활성)", example = "true")
    private boolean enable;
}
