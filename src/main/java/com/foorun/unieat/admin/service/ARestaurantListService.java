package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.repository.ARestaurantRepository;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class ARestaurantListService {
    private final ARestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public Page<Restaurant> fetch(Paging paging) {
        return restaurantRepository.findAll(paging)
                .map(Restaurant::of);
    }

    @Transactional(readOnly = true)
    public Page<Restaurant> search(String keyword, Paging paging) {
        Page<RestaurantJpo> page;
        if (hasText(keyword)) {
            page = restaurantRepository.findByKeyword(keyword, paging);
        } else {
            page = restaurantRepository.findAll(paging);
        }
        return page.map(Restaurant::of);
    }
}
