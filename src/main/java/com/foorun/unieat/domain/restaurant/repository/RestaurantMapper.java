package com.foorun.unieat.domain.restaurant.repository;

import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    List<Restaurant> findNearest(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("nearBy") double nearBy);
}
