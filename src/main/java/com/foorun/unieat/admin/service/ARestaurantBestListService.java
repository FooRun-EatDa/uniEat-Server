package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.ARestaurant;
import com.foorun.unieat.admin.domain.restaurant.dto.ARestaurantBestDeletePayload;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantBestJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantBestQuerydslRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantBestRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ARestaurantBestListService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantBestRepository restaurantBestRepository;
    private final RestaurantBestQuerydslRepository restaurantBestQuerydslRepository;

    @Transactional(readOnly = true)
    public List<ARestaurant> fetch() {
        return restaurantBestQuerydslRepository.findAllFetchJoin()
                .stream()
                .map(best -> ARestaurant.simpleOf(best.getRestaurant()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void remove(String restaurantIdsJoinedStr) {
        List<Long> restaurantIds = Arrays
                .stream(restaurantIdsJoinedStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<RestaurantJpo> restaurants = restaurantRepository.findAllById(restaurantIds);
        restaurantBestRepository.deleteAllByRestaurantIn(restaurants);
    }

    @Transactional
    public void saveAll(ARestaurantBestDeletePayload payload) {
        List<RestaurantBestJpo> bests = restaurantRepository
                .findAllById(payload.getRestaurantIds())
                .stream()
                .map(restaurant -> RestaurantBestJpo.builder()
                        .id(0L)
                        .restaurant(restaurant)
                        .regionCode(restaurant.getRegionCode())
                        .build())
                .collect(Collectors.toList());
        restaurantBestRepository.saveAll(bests);
    }
}
