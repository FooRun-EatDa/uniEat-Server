package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.ARestaurantHashTag;
import com.foorun.unieat.domain.hashtag.repository.HashTagRestaurantRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantQuerydslRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ARestaurantHashTagService {
    private final HashTagRestaurantRepository hashTagRestaurantRepository;
    private final RestaurantQuerydslRepository restaurantQuerydslRepository;

    @Transactional(readOnly = true)
    public List<ARestaurantHashTag> fetch(long restaurantId) {
        RestaurantJpo restaurantJpo = restaurantQuerydslRepository.find(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        return hashTagRestaurantRepository.findByRestaurant(restaurantJpo)
                .stream()
                .map(ARestaurantHashTag::of)
                .collect(Collectors.toList());
    }
}
