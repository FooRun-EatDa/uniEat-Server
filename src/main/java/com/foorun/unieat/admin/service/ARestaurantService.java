package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.repository.ARestaurantRepository;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ARestaurantService {
    private final ARestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public Restaurant fetch(long id) {
        return Restaurant.of(restaurantRepository.findById(id)
                .orElseThrow(UniEatNotFoundException::new));
    }

    @Transactional
    public long save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.generateId();
        }
        restaurantRepository.save(restaurant.asJpo());
        return restaurant.generateId();
    }

    @Transactional
    public long update(Restaurant restaurant) {
        RestaurantJpo restaurantJpo = restaurantRepository.findById(restaurant.getId())
                .orElseThrow(UniEatNotFoundException::new);
        restaurant.applyRevision(restaurantJpo);
        restaurantRepository.save(restaurantJpo);
        return restaurant.generateId();
    }
}
