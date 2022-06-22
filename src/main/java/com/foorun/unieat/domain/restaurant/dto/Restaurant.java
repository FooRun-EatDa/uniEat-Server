package com.foorun.unieat.domain.restaurant.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.category.dto.Category;
import com.foorun.unieat.domain.code.region.dto.RegionCode;
import com.foorun.unieat.domain.food.dto.Food;
import com.foorun.unieat.domain.food.jpo.FoodJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.review.dto.Review;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.foorun.unieat.util.StreamUtil.map;
import static com.foorun.unieat.util.StreamUtil.mapToSet;
import static org.springframework.util.CollectionUtils.isEmpty;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean isLiked; // 유저가 좋아요 한 식당인지

    private RegionCode regionCode;
    private List<Category> categories;
    private List<Food> foods;
    private List<Review> reviews;

    public static Restaurant createEmpty(){return new Restaurant();}

    public static Restaurant of(RestaurantJpo restaurantJpo){
        Restaurant restaurant = createEmpty();
        BeanUtils.copyProperties(restaurantJpo,restaurant);

        restaurant.categories = map(restaurantJpo.getCategorys(),Category::of);
        restaurant.foods = map(restaurantJpo.getFoods(),Food::of);
        restaurant.reviews = map(restaurantJpo.getReviews(),Review::of);

        return restaurant;
    }

    public void applyRevision(RestaurantJpo restaurantJpo) {
        BeanUtils.copyProperties(this, restaurantJpo);
        restaurantJpo.setUpdatedAt(LocalDateTime.now());
    }

    public RestaurantJpo asJpo() {
        RestaurantJpo restaurantJpo = new RestaurantJpo();
        BeanUtils.copyProperties(this,restaurantJpo);
        if (!isEmpty(categories)) {
            restaurantJpo.setCategorys(mapToSet(this.categories, Category::asJpo));
        }
        if (!isEmpty(foods)) {
            Set<FoodJpo> foodJpoSet = this.foods.stream().map(r -> r.asJpo(restaurantJpo)).collect(Collectors.toSet());
            restaurantJpo.setFoods(foodJpoSet);
            restaurantJpo.setReviews(mapToSet(this.reviews,Review::asJpo));
        }
        return restaurantJpo;
    }


    public long generateId() {
        this.id = IdentifyGenerator.number();
        return this.id;
    }
}
