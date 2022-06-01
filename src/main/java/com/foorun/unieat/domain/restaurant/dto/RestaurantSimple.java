package com.foorun.unieat.domain.restaurant.dto;

import com.foorun.unieat.domain.hashtag.dto.HashTageRestaurant;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

import static com.foorun.unieat.util.StreamUtil.map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RestaurantSimple {
    private Long id;
    private String name;
    private String explanation;
    private String imgUrl;
    private double distance;
    private List<String> hashTags;

    public static RestaurantSimple createEmpty() {
        return new RestaurantSimple();
    }


    public static RestaurantSimple of(RestaurantJpo restaurantJpo){
        RestaurantSimple restaurantSimple = createEmpty();
        restaurantSimple.hashTags = map(restaurantJpo.getHashTagRestaurants(),h-> {
            return h.getHashTag().getContent();
        });

        BeanUtils.copyProperties(restaurantJpo,restaurantSimple);
        return restaurantSimple;
    }

}
