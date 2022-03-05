package com.foorun.unieat.client.payload;

import com.foorun.unieat.client.constant.PigeonMethod;
import com.foorun.unieat.client.constant.PigeonMode;
import com.foorun.unieat.constant.EmailVerifyType;
import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Pigeon 클라이언트 요청 양식
 * @param <T> 타입별(이메일, SMS 등) 요청 양식
 */
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PigeonRequest<T extends PigeonForm> implements JsonSerializable {
    private PigeonMethod method;
    private PigeonMode mode;
    private LocalDateTime createdAt;
    private T form;

    public static PigeonRequest<PigeonForm> formSingleEmailOfVerificationCode(EmailVerifyType emailVerifyType, String to, Long verificationCode) {
        return PigeonRequest.builder()
                .form(PigeonEmailForm.builder()
                        .to(to)
                        .subject("Uni-Eat 이메일 인증 코드입니다.")
                        .templateName(emailVerifyType.getTemplateName())
                        .useTemplate(true)
                        .build()
                        .addProperty("verificationCode", verificationCode))
                .mode(PigeonMode.SINGLE)
                .method(PigeonMethod.EMAIL)
                .build();
    }
}
