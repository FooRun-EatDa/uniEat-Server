package com.foorun.unieat.domain.review.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","content","imgUrl","starScore"})
public class ReviewUpdateReq {

    private Long id;
    private String content;
    private String imgUrl; //리뷰 이미지
    private int starScore;

    public ReviewJpo asJpo(){
        ReviewJpo review = new ReviewJpo();
        BeanUtils.copyProperties(this,review);
        return review;
    }
}
