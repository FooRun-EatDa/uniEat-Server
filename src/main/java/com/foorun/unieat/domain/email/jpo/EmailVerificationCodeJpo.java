package com.foorun.unieat.domain.email.jpo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "email_verification_code")
public class EmailVerificationCodeJpo {
    /**
     * 이메일
     */
    @Id
    @Column
    private String email;

    /**
     * 발급된 인증 코드
     */
    @Column
    private String code;

    /**
     * 발급일시
     */
    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public static EmailVerificationCodeJpo of(String email, String code) {
        return builder()
                .email(email)
                .code(code)
                .build();
    }
}
