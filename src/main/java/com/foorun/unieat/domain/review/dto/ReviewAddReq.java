package com.foorun.unieat.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewAddReq {


    private String content;
    private String imgUrl; //리뷰 이미지
    private int starScore;
    private Long restaurantId;

    public ReviewJpo asJpo(){
        return ReviewJpo.builder()
                .id(IdentifyGenerator.number())
                .content(this.content)
                .starScore(Objects.isNull(this.getStarScore()) ? 0 : this.getStarScore())
                .imgUrl(this.imgUrl)
                .build();
    }
}
