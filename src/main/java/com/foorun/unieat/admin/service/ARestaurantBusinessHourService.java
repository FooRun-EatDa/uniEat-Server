package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.ARestaurantBusinessHour;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantBusinessHourRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantQuerydslRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ARestaurantBusinessHourService {
    private final RestaurantQuerydslRepository restaurantQuerydslRepository;
    private final RestaurantBusinessHourRepository restaurantBusinessHourRepository;

    @Transactional(readOnly = true)
    public List<ARestaurantBusinessHour> fetch(long restaurantId) {
        RestaurantJpo restaurantJpo = restaurantQuerydslRepository.find(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        return restaurantBusinessHourRepository.findByIdRestaurant(restaurantJpo)
                .stream()
                .map(ARestaurantBusinessHour::of)
                .collect(Collectors.toList());
    }
}
