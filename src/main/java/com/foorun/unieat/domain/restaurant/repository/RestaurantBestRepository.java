package com.foorun.unieat.domain.restaurant.repository;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantBestJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantBestRepository extends JpaRepository<RestaurantBestJpo, Long> {
    
}
