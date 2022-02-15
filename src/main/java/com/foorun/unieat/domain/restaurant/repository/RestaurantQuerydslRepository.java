package com.foorun.unieat.domain.restaurant.repository;


import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RestaurantQuerydslRepository implements QuerydslSelectMulti<RestaurantJpo>, QuerydslSelectSingle<RestaurantJpo, Long> {


    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RestaurantJpo> find(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RestaurantJpo> find(Long aLong) {
        return Optional.empty();
    }
}
