package com.foorun.unieat.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewReq {

    private Long id; // 리뷰 id
    private Long restaurantId;
    private String content;
    private String imgUrl; //리뷰 이미지
    private int starScore;


    public ReviewJpo asJpo(){
        ReviewJpo review = new ReviewJpo();
        BeanUtils.copyProperties(this,review);
        return review;
    }


    public Review updateReviewByReq(Review review){

        review.setContent(Optional.of(this.content).orElse(review.getContent()));
        review.setImgUrl(Optional.of(this.imgUrl).orElse(review.getImgUrl()));
        review.setStarScore(Optional.of(this.starScore).orElse(review.getStarScore()));
        return review;
    }


}
