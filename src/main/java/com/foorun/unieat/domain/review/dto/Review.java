package com.foorun.unieat.domain.review.dto;

import com.foorun.unieat.domain.feeling.dto.ReviewFeeling;
import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import lombok.*;

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



}
