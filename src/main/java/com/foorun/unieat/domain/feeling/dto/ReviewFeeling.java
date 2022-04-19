package com.foorun.unieat.domain.feeling.dto;


import com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo;
import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.review.dto.Review;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ReviewFeeling {

    private Long id;

    private Member member;

    private Review review;


    public static ReviewFeeling of(ReviewFeelingJpo reviewFeelingJpo){
        ReviewFeeling reviewFeeling = new ReviewFeeling();
        BeanUtils.copyProperties(reviewFeelingJpo,reviewFeeling);
        reviewFeeling.member = Member.of(reviewFeelingJpo.getMember());
        reviewFeeling.review = Review.of(reviewFeelingJpo.getReview());
        return reviewFeeling;
    }

}
