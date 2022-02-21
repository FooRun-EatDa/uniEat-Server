package com.foorun.unieat.domain.email.jpo;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "email_verification_code")
@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static EmailVerificationCodeJpo of(String email, String code) {
        return builder()
                .email(email)
                .code(code)
                .build();
    }
}
