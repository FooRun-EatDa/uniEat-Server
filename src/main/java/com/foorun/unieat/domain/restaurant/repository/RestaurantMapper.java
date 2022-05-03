package com.foorun.unieat.domain.restaurant.repository;

import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    List<RestaurantSimple> findNearest(double latitude, double longitude, double nearBy);
}
