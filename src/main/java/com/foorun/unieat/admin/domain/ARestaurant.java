package com.foorun.unieat.admin.domain;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.category.dto.Category;
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

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ARestaurant implements JsonSerializable {
    private Long id;
    private String name;
    private String explanation;
    private String imgUrl;
    private String content;
    private Integer category;
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
    private Long districtCode;
    private List<Category> categories;
    private List<Food> foods;
    private List<Review> reviews;

    public static ARestaurant createEmpty() {
        return new ARestaurant();
    }

    public static ARestaurant simpleOf(RestaurantJpo restaurantJpo) {
        ARestaurant restaurant = createEmpty();
        BeanUtils.copyProperties(restaurantJpo,restaurant);
        return restaurant;
    }

    public static ARestaurant of(RestaurantJpo restaurantJpo) {
        ARestaurant restaurant = createEmpty();
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
            restaurantJpo.setReviews(mapToSet(this.reviews, Review::asJpo));
        }
        return restaurantJpo;
    }

    public long generateId() {
        this.id = IdentifyGenerator.number();
        return this.id;
    }
}
