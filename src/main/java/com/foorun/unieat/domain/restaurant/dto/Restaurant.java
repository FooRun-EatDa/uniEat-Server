package com.foorun.unieat.domain.restaurant.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Restaurant implements JsonSerializable {

    private Long id;
    private String name;
    private String explanation;
    private String imgUrl;
    private String content;
    private String address;
    private String longitude;
    private String latitude;
    private String phoneNumber;
    private String operationTime;
    private int price;
    private String district;
    private String status;

    public static Restaurant createEmpty(){return new Restaurant();}

    public static Restaurant of(RestaurantJpo restaurantJpo){
        Restaurant restaurant = createEmpty();
        BeanUtils.copyProperties(restaurantJpo,restaurant);
        return restaurant;
    }

    public RestaurantJpo asJpo(){
        RestaurantJpo restaurantJpo = new RestaurantJpo();
        BeanUtils.copyProperties(this,restaurantJpo);
        return restaurantJpo;
    }



}
