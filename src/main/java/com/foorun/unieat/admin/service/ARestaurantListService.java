package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.ARestaurant;
import com.foorun.unieat.admin.repository.ARestaurantQuerydslRepository;
import com.foorun.unieat.admin.repository.ARestaurantRepository;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class ARestaurantListService {
    private final ARestaurantRepository restaurantRepository;
    private final ARestaurantQuerydslRepository restaurantQuerydslRepository;

    @Transactional(readOnly = true)
    public Page<ARestaurant> fetch(Paging paging) {
        return restaurantRepository.findAll(paging)
                .map(ARestaurant::simpleOf);
    }

    @Transactional(readOnly = true)
    public Page<ARestaurant> search(String keyword, String categories, String hashTags, Paging paging) {
        ARestaurantQuerydslRepository.Query.QueryBuilder queryBuilder = ARestaurantQuerydslRepository.Query.builder()
                .keyword(keyword)
                .paging(paging);
        if (hasText(categories)) {
            queryBuilder.categories(Arrays.stream(categories.split(",")).mapToLong(Long::parseLong).boxed().collect(Collectors.toList()));
        }
        if (hasText(hashTags)) {
            queryBuilder.hashTags(Arrays.stream(hashTags.split(",")).collect(Collectors.toList()));
        }
        Page<RestaurantJpo> page = restaurantQuerydslRepository.find(queryBuilder.build());
        return page.map(ARestaurant::simpleOf);
    }
}
