package com.foorun.unieat.domain.hashtag.dto;

import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HashTageRestaurant {

    private Long id;

    private HashTag hashTag;

    private Restaurant restaurant;
}
