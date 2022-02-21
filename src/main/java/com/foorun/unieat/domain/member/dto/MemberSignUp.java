package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.Validatable;
import com.foorun.unieat.domain.common.Gender;
import com.foorun.unieat.exception.UniEatBadRequestException;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import static com.foorun.unieat.util.RegexUtil.REGEX_EMAIL;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignUp implements JsonSerializable, Validatable {
    @ApiModelProperty(notes = "이메일", example = "cha2.hoon@gmail.com")
    private String email;

    @ApiModelProperty(notes = "비밀번호", example = "1")
    private String password;

    @ApiModelProperty(notes = "비밀번호 재입력", example = "1")
    private String passwordRe;

    @ApiModelProperty(notes = "성명", example = "임채훈")
    private String name;

    @ApiModelProperty(notes = "이메일 인증 코드", example = "999999")
    private String verificationCode;

    @ApiModelProperty(notes = "주민등록번호", example = "960713-1111111")
    private String rrn;

    @ApiModelProperty(notes = "성별", example = "MALE")
    private Gender gender;

    @ApiModelProperty(notes = "학교 ID", example = "1253855")
    private Long schoolId;

    @ApiModelProperty(notes = "개인정보 수집 동의 여부", example = "true")
    private boolean isAgreeTerms;

    @ApiModelProperty(notes = "이벤트 수신 동의 여부", example = "true")
    private boolean isAgreeEventLetter;

    @Override
    public void validate() {
        if (!REGEX_EMAIL.matcher(email).matches()) {
            throw new UniEatBadRequestException();
        }
    }
}