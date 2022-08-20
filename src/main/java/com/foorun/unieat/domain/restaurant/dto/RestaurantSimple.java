package com.foorun.unieat.domain.restaurant.dto;

import com.foorun.unieat.domain.hashtag.dto.HashTagRestaurant;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

import static com.foorun.unieat.util.StreamUtil.map;
/**
 * 위치정보를 위한
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RestaurantSimple{
    private Long id;
    private String name;
    private String explanation;
    private String imgUrl;
    private double distance;
    private double longitude;
    private double latitude;

    //맛집인가?
    private Boolean isUniEatSelected;

    private boolean isLiked; // 유저가 좋아요한 식당인지 여부
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
        restaurantSimple.hashTags = map(restaurantJpo.getHashTagRestaurants(), HashTagRestaurant::extractHashTag);
        return restaurantSimple;
    }

}
