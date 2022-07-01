package com.foorun.unieat.admin.domain;

import com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ARestaurantHashTag {
    private long restaurantId;
    private long hashTagId;
    private String content;

    public static ARestaurantHashTag of(HashTagRestaurantJpo hashTagRestaurantJpo) {
        return ARestaurantHashTag.builder()
                .hashTagId(hashTagRestaurantJpo.getHashTag().getId())
                .restaurantId(hashTagRestaurantJpo.getRestaurant().getId())
                .content(hashTagRestaurantJpo.getHashTag().getContent())
                .build();
    }
}
