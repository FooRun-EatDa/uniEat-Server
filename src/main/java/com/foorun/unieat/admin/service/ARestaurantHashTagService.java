package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.ARestaurantHashTag;
import com.foorun.unieat.domain.hashtag.jpo.HashTagJpo;
import com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo;
import com.foorun.unieat.domain.hashtag.repository.HashTagRepository;
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
    private final HashTagRepository hashTagRepository;

    @Transactional(readOnly = true)
    public List<ARestaurantHashTag> fetch(long restaurantId) {
        RestaurantJpo restaurantJpo = restaurantQuerydslRepository.find(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        return hashTagRestaurantRepository.findByRestaurant(restaurantJpo)
                .stream()
                .map(ARestaurantHashTag::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(long restaurantId, List<Long> hashTagIds) {
        RestaurantJpo restaurantJpo = restaurantQuerydslRepository.find(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        List<HashTagJpo> hashTagJpos = hashTagRepository.findByIdIn(hashTagIds);
//        hashTagRestaurantRepository.deleteByRestaurantAndHashTagNotIn(restaurantJpo, hashTagJpos);
        hashTagRestaurantRepository.deleteByRestaurant(restaurantJpo);
        hashTagRestaurantRepository.saveAll(hashTagJpos
                .stream()
                .map(hashTagJpo -> HashTagRestaurantJpo.builder()
                        .restaurant(restaurantJpo)
                        .hashTag(hashTagJpo)
                        .build())
                .collect(Collectors.toList()));
    }

    @Transactional
    public void removeHard(long restaurantId, long hashTagId) {
        RestaurantJpo restaurantJpo = restaurantQuerydslRepository.find(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        HashTagJpo hashTagJpo = hashTagRepository.findById(hashTagId)
                .orElseThrow(UniEatNotFoundException::new);
        hashTagRestaurantRepository.deleteByRestaurantAndHashTag(restaurantJpo, hashTagJpo);
    }
}
