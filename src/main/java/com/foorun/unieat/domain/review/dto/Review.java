package com.foorun.unieat.domain.review.dto;

import com.foorun.unieat.domain.feeling.dto.ReviewFeeling;
import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

import static com.foorun.unieat.util.StreamUtil.map;
import static com.foorun.unieat.util.StreamUtil.mapToSet;

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



    public static Review of(ReviewJpo reviewJpo){

        Review review = new Review();
        BeanUtils.copyProperties(reviewJpo,review);
        review.restaurant = Restaurant.of(reviewJpo.getRestaurant());
        review.member = Member.of(reviewJpo.getMember());
        review.reviewFeelings = map(reviewJpo.getReviewFeelings(),ReviewFeeling::of);
        return review;

    }


    public ReviewJpo asJpo(){

        ReviewJpo reviewJpo = new ReviewJpo();
        BeanUtils.copyProperties(this,reviewJpo);
        reviewJpo.setRestaurant(this.restaurant.asJpo());
        reviewJpo.setMember(this.member.asJpo());
        reviewJpo.setReviewFeelings(mapToSet(this.reviewFeelings,ReviewFeeling::asJpo));

        return reviewJpo;
    }


}
