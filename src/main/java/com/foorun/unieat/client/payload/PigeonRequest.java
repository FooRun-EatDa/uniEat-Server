package com.foorun.unieat.client.payload;

import com.foorun.unieat.client.constant.PigeonMethod;
import com.foorun.unieat.client.constant.PigeonMode;
import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;

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

    public static PigeonRequest<PigeonForm> formSingleEmailOf(String content, String... email) {
        return PigeonRequest.builder()
                .form(PigeonEmailForm.of(content, Arrays.asList(email)))
                .mode(PigeonMode.SINGLE)
                .method(PigeonMethod.EMAIL)
                .build();
    }
}
