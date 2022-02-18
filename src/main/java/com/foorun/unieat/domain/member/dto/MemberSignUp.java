package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.common.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class MemberSignUp implements JsonSerializable {
    @ApiModelProperty(notes = "이메일", example = "cha2.hoon@gmail.com")
    private final String email;

    @ApiModelProperty(notes = "비밀번호", example = "1")
    private final String password;

    @ApiModelProperty(notes = "비밀번호 재입력", example = "1")
    private final String passwordRe;

    @ApiModelProperty(notes = "성명", example = "임채훈")
    private final String name;

    @ApiModelProperty(notes = "이메일 인증 코드", example = "999999")
    private final String verificationCode;

    @ApiModelProperty(notes = "주민등록번호", example = "960713-1111111")
    private final String rrn;

    @ApiModelProperty(notes = "성별", example = "MALE")
    private final Gender gender;

    @ApiModelProperty(notes = "학교 ID", example = "1253855")
    private final Long schoolId;

    @ApiModelProperty(notes = "개인정보 수집 동의 여부", example = "true")
    private final boolean isAgreeTerms;

    @ApiModelProperty(notes = "이벤트 수신 동의 여부", example = "true")
    private final boolean isAgreeEventLetter;
}
