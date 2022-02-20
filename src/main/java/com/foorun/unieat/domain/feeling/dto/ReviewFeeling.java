package com.foorun.unieat.domain.feeling.dto;


import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.review.dto.Review;
import lombok.*;

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


}
