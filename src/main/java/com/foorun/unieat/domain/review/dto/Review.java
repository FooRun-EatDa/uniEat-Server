package com.foorun.unieat.domain.review.dto;

import com.foorun.unieat.domain.feeling.dto.ReviewFeeling;
import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

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


}
