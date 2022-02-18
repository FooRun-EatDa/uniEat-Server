package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class MemberSignIn implements JsonSerializable {
    private final String email;
    private final String password;
    private final boolean isAuto = false;
}
