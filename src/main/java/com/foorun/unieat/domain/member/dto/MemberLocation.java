package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 주변맛집 서비스에 이용되는 유저 위치 정보
 */
public class MemberLocation implements JsonSerializable {
    private float longitude;
    private float latitude;


}
