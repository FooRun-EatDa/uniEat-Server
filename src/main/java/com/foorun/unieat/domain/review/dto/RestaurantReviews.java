package com.foorun.unieat.domain.review.dto;

import com.foorun.unieat.domain.review.StarScore;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RestaurantReviews {

    private Map<String,Long> scoreCount;
    private List<Review> reviews;
}
