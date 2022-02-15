package com.foorun.unieat.domain.restaurant.dto;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

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

    public static RestaurantSimple createEmpty() {
        return new RestaurantSimple();
    }


    public static RestaurantSimple of(RestaurantJpo restaurantJpo){
        RestaurantSimple restaurantSimple = createEmpty();
        BeanUtils.copyProperties(restaurantJpo,restaurantSimple);
        return restaurantSimple;
    }

}
