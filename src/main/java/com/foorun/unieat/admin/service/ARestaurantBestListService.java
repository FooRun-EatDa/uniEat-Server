package com.foorun.unieat.admin.service;

import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.repository.RestaurantBestQuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ARestaurantBestListService {
    private final RestaurantBestQuerydslRepository restaurantBestQuerydslRepository;

    public List<Restaurant> fetch() {
        return restaurantBestQuerydslRepository.findAllFetchJoin()
                .stream()
                .map(best -> Restaurant.of(best.getRestaurant()))
                .collect(Collectors.toList());
    }
}
