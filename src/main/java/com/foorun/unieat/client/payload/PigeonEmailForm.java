package com.foorun.unieat.client.payload;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PigeonEmailForm implements PigeonForm {
    private String to;
    private String subject;
    private String content;

    /**
     * 별도의 HTML 템플릿 사용 여부
     */
    @Builder.Default
    private boolean useTemplate = false;

    /**
     * 사용할 이메일 템플릿명(디렉토리를 포함한 HTML 파일 경로)<br />
     * ex) ${RESOURCE_SERVER_HOST}/${RESOURCE_SERVER_BASE_URI}/email/verification-email.html 일 경우<br />
     *  Template Resolver 설정의 Prefix, Suffix 를 제외한 --> email/verification-email
     */
    private String templateName;

    /**
     * 이메일 템플릿에 Binding 할 파라미터
     */
    @Builder.Default
    private Map<String, Object> properties = new HashMap<>();

    public PigeonEmailForm addProperty(String key, Object value) {
        this.properties.put(key, value);
        return this;
    }
}
