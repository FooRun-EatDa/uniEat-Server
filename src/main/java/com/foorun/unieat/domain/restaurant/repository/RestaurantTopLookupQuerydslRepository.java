package com.foorun.unieat.domain.restaurant.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantTopLookupQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;


}
