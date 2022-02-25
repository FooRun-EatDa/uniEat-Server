package com.foorun.unieat.domain.history.member.jpo;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
@Table(name = "history_member_sign_in")
@EntityListeners(AuditingEntityListener.class)
public class HistoryMemberSignInJpo implements Persistable<Long> {
    @Id
    @Builder.Default
    private Long no = 0L;
    private Long memberId;

    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public Long getId() {
        return no;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    /**
     * static factory method
     * @param memberId 사용자 고유 ID
     */
    public static HistoryMemberSignInJpo of(Long memberId) {
        return builder()
                .memberId(memberId)
                .build();
    }
}
