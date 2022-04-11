package com.foorun.unieat.domain.feeling.jpo;

import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
/**
 * 리뷰 글에 대한 좋아요 여부 정보
 */
@Table(name = "review_feeling")
public class ReviewFeelingJpo {
    @Id
    @Column(name="member_review_feeling_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @ToString.Exclude
    private MemberJpo member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    @ToString.Exclude
    private ReviewJpo review;

}
