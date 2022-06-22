package com.foorun.unieat.domain.restaurant.repository;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantBestJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantBestRepository extends JpaRepository<RestaurantBestJpo, Long> {
    void deleteAllByRestaurantIn(List<RestaurantJpo> restaurants);
}
