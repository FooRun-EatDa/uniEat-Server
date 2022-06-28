package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.ARestaurant;
import com.foorun.unieat.admin.repository.ARestaurantRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ARestaurantService {
    private final ARestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public ARestaurant fetch(long id) {
        return ARestaurant.of(restaurantRepository.findById(id)
                .orElseThrow(UniEatNotFoundException::new));
    }

    @Transactional
    public long save(ARestaurant restaurant) {
        long id = Optional.ofNullable(restaurant.getId())
                .orElseGet(restaurant::generateId);
        restaurantRepository.save(restaurant.asJpo());
        return id;
    }

    @Transactional
    public long update(ARestaurant restaurant) {
        RestaurantJpo restaurantJpo = restaurantRepository.findById(restaurant.getId())
                .orElseThrow(UniEatNotFoundException::new);
        restaurant.applyRevision(restaurantJpo);
        restaurantRepository.save(restaurantJpo);
        return restaurant.generateId();
    }

    @Transactional
    public void removeHard(long id) {
        restaurantRepository.deleteById(id);
    }
}
