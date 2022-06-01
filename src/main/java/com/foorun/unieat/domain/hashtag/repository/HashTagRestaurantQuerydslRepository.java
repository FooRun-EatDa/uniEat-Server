package com.foorun.unieat.domain.hashtag.repository;


import com.foorun.unieat.domain.hashtag.jpo.QHashTagJpo;
import com.foorun.unieat.domain.hashtag.jpo.QHashTagRestaurantJpo;
import com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.foorun.unieat.domain.hashtag.jpo.QHashTagJpo.*;
import static com.foorun.unieat.domain.hashtag.jpo.QHashTagRestaurantJpo.hashTagRestaurantJpo;
import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo.restaurantJpo;

@Repository
@RequiredArgsConstructor
public class HashTagRestaurantQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public List<String> getHashTagContentByRestaurantId(Long restaurantId){
        return jpaQueryFactory.select(hashTagJpo.content).from(hashTagJpo
        ).leftJoin(hashTagRestaurantJpo).on(
                hashTagRestaurantJpo.restaurant.id.eq(restaurantId)
                ,hashTagRestaurantJpo.hashTag.id.eq(hashTagJpo.id)
        ).fetch();

    }
}
