package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.ARestaurant;
import com.foorun.unieat.admin.repository.ARestaurantRepository;
import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import com.foorun.unieat.domain.code.region.repository.RegionCodeRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ARestaurantService {
    private final ARestaurantRepository restaurantRepository;
    private final RegionCodeRepository regionCodeRepository;

    @Transactional(readOnly = true)
    public ARestaurant fetch(long id) {
        return ARestaurant.of(restaurantRepository.findById(id)
                .orElseThrow(UniEatNotFoundException::new));
    }

    @Transactional
    public long save(ARestaurant restaurant) {
        long id = Optional.ofNullable(restaurant.getId())
                .orElseGet(restaurant::generateId);
        RestaurantJpo restaurantJpo = restaurant.asJpo();
        if (nonNull(restaurant.getDistrictCode())) {
            RegionCodeJpo regionCodeJpo = regionCodeRepository.findById(restaurant.getDistrictCode())
                    .orElse(null);
            restaurantJpo.setRegionCode(regionCodeJpo);
        }
        restaurantRepository.save(restaurantJpo);
        return id;
    }

    @Transactional
    public long update(ARestaurant restaurant) {
        RestaurantJpo restaurantJpo = restaurantRepository.findById(restaurant.getId())
                .orElseThrow(UniEatNotFoundException::new);
        restaurant.applyRevision(restaurantJpo);
        if (nonNull(restaurant.getDistrictCode())) {
            RegionCodeJpo regionCodeJpo = regionCodeRepository.findById(restaurant.getDistrictCode())
                    .orElse(null);
            restaurantJpo.setRegionCode(regionCodeJpo);
        }
        restaurantRepository.save(restaurantJpo);
        return restaurant.generateId();
    }

    @Transactional
    public void removeHard(long id) {
        restaurantRepository.deleteById(id);
    }
}
