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
    private String tag;

    public static ARestaurantHashTag of(HashTagRestaurantJpo hashTagRestaurantJpo) {
        return ARestaurantHashTag.builder()
                .restaurantId(hashTagRestaurantJpo.getRestaurant().getId())
                .tag(hashTagRestaurantJpo.getHashTag().getContent())
                .build();
    }
}
