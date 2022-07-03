package com.foorun.unieat.domain.restaurant.repository;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantBusinessHourJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.foorun.unieat.domain.restaurant.jpo.RestaurantBusinessHourJpo.Identify;

@Repository
public interface RestaurantBusinessHourRepository extends JpaRepository<RestaurantBusinessHourJpo, Identify> {
    List<RestaurantBusinessHourJpo> findByIdRestaurant(RestaurantJpo restaurant);
    void deleteByIdRestaurantAndIdContentNotIn(RestaurantJpo restaurant, List<String> contents);
    void deleteByIdRestaurant(RestaurantJpo restaurantJpo);
}
