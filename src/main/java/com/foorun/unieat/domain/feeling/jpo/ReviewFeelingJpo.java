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
public class ReviewFeelingJpo {
    @Id
    @Column(name="member_review_feeling_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name="member_id")
    private MemberJpo member;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name="review_id")
    private ReviewJpo review;

}
