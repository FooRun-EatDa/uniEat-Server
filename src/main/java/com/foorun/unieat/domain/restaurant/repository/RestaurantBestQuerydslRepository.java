package com.foorun.unieat.domain.restaurant.repository;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantBestJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantBestJpo.restaurantBestJpo;

@Repository
@RequiredArgsConstructor
public class RestaurantBestQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<RestaurantBestJpo> findAllFetchJoin() {
        return jpaQueryFactory.selectFrom(restaurantBestJpo)
                .innerJoin(restaurantBestJpo.restaurant)
                .fetchJoin()
                .innerJoin(restaurantBestJpo.regionCode)
                .fetchJoin()
                .fetch();
    }
}
