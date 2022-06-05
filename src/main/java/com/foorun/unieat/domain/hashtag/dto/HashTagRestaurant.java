package com.foorun.unieat.domain.hashtag.dto;

import com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HashTagRestaurant {

    private Long id;

    private HashTag hashTag;

    private Restaurant restaurant;


    public static String extractHashTag(HashTagRestaurantJpo hashTagRestaurantJpo){
        return hashTagRestaurantJpo.getHashTag().getContent();

    }


}
