package com.foorun.unieat.domain.hashtag.repository;

import com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTagRestaurantRepository extends JpaRepository<HashTagRestaurantJpo, Long> {
    List<HashTagRestaurantJpo> findByRestaurant(RestaurantJpo restaurantJpo);
}
