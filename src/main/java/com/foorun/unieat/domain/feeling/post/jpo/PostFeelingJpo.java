package com.foorun.unieat.domain.feeling.post.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.post.jpo.PostJpo;
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
@Table(name = "post_feeling")
@IdClass(PostFeelingIdJpo.class)
@EntityListeners(AuditingEntityListener.class)
public class PostFeelingJpo implements JsonSerializable {
    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostJpo post;

    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpo member;

    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * static factory method
     */
    public static PostFeelingJpo of(PostJpo postJpo, MemberJpo memberJpo) {
        return builder()
                .post(postJpo)
                .member(memberJpo)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
