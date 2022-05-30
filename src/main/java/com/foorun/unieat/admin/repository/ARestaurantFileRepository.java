package com.foorun.unieat.admin.repository;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantFileIdJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantFileJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ARestaurantFileRepository extends JpaRepository<RestaurantFileJpo, RestaurantFileIdJpo> {
}
