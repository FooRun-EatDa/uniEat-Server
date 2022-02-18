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
    @Id
    @Column(columnDefinition = "이메일")
    private String email;

    @Column(columnDefinition = "발급된 인증 코드")
    private String code;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "발급일시")
    private LocalDateTime createdAt;

    public static EmailVerificationCodeJpo of(String email, String code) {
        return builder()
                .email(email)
                .code(code)
                .build();
    }
}
