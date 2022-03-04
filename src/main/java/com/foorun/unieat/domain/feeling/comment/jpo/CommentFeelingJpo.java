package com.foorun.unieat.domain.feeling.comment.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "comment_feeling")
@IdClass(CommentFeelingIdJpo.class)
@EntityListeners(AuditingEntityListener.class)
public class CommentFeelingJpo implements JsonSerializable {
    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpo member;

    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentJpo comment;

    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * static factory method
     */
    public static CommentFeelingJpo of(MemberJpo memberJpo, CommentJpo commentJpo) {
        return builder()
                .member(memberJpo)
                .comment(commentJpo)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
