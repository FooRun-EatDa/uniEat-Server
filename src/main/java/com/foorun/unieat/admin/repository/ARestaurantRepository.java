package com.foorun.unieat.admin.repository;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ARestaurantRepository extends JpaRepository<RestaurantJpo, Long> {
    @Query("SELECT R FROM RestaurantJpo R WHERE R.name LIKE %:keyword%")
    Page<RestaurantJpo> findByKeyword(String keyword, Pageable pageable);
}
