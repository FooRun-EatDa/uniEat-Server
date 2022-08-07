package com.foorun.unieat.domain.hashtag.dto;


import com.foorun.unieat.domain.hashtag.jpo.HashTagJpo;
import com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HashTag {
    private Long id;
    private String content;

    private List<HashTagRestaurant> hashTagRestaurants;

    public static HashTag of(HashTagJpo hashTagJpo) {
        return HashTag.builder()
                .id(hashTagJpo.getId())
                .content(hashTagJpo.getContent())
                .build();
    }

    public static HashTag of(HashTagRestaurantJpo hashTagRestaurantJpo) {
        HashTagJpo hashTagJpo = hashTagRestaurantJpo.getHashTag();
        return HashTag.builder()
                .id(hashTagJpo.getId())
                .content(hashTagJpo.getContent())
                .build();
    }

    public static String ofContent(HashTagRestaurantJpo hashTagRestaurantJpo) {
        return hashTagRestaurantJpo.getHashTag().getContent();
    }
}
