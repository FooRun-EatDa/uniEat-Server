package com.foorun.unieat.domain.restaurant.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.category.dto.Category;
import com.foorun.unieat.domain.code.region.dto.RegionCode;
import com.foorun.unieat.domain.code.region.dto.embed.Coordinate;
import com.foorun.unieat.domain.food.dto.Food;
import com.foorun.unieat.domain.food.jpo.FoodJpo;
import com.foorun.unieat.domain.hashtag.dto.HashTag;
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
    private String phoneNumber;
    private String operationTime;
    private int price;
    private String district;
    private String status;
    private Coordinate coordinate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean isUniEatSelected; //유니잇 맛집인지 확인


    private boolean isLiked; // 유저가 좋아요 한 식당인지

    private RegionCode regionCode;
    private List<Category> categories;
    private List<Food> foods;
    private List<Review> reviews;
    private List<String> hashTags;

    public static Restaurant createEmpty(){return new Restaurant();}

    public static Restaurant of(RestaurantJpo restaurantJpo){
        Restaurant restaurant = createEmpty();
        BeanUtils.copyProperties(restaurantJpo,restaurant);
        restaurant.coordinate = Coordinate.of(restaurantJpo.getLatitude(), restaurantJpo.getLongitude());
        restaurant.categories = map(restaurantJpo.getCategorys(),Category::of);
        restaurant.foods = map(restaurantJpo.getFoods(),Food::of);
        restaurant.reviews = map(restaurantJpo.getReviews(),Review::of);
        restaurant.hashTags = map(restaurantJpo.getHashTagRestaurants(), HashTag::ofContent);
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
