package com.foorun.unieat.domain.review.dto;

import com.foorun.unieat.domain.feeling.dto.ReviewFeeling;
import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

import static com.foorun.unieat.util.StreamUtil.map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Review {
    private Long id;
    private String content;
    private int starScore;

    private Restaurant restaurant;
    private Member member;
    private List<ReviewFeeling> reviewFeelings;


    public ReviewJpo asJpo(){
        ReviewJpo reviewJpo = new ReviewJpo();
        BeanUtils.copyProperties(this,reviewJpo);
        return reviewJpo;

    }

    public static Review of(ReviewJpo reviewJpo){
        Review review = new Review();
        BeanUtils.copyProperties(reviewJpo,review);
        review.member = Member.of(reviewJpo.getMember());
        review.restaurant = Restaurant.of(reviewJpo.getRestaurant());
        review.reviewFeelings = map(reviewJpo.getReviewFeelings(),ReviewFeeling::of);

        return review;

    }


}
