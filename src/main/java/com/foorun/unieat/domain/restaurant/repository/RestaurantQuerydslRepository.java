package com.foorun.unieat.domain.restaurant.repository;


import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo.restaurantJpo;

@Repository
@RequiredArgsConstructor
public class RestaurantQuerydslRepository implements QuerydslSelectMulti<RestaurantJpo>, QuerydslSelectSingle<RestaurantJpo, Long> {


    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 심플 식당 정보 다건 조회,
     * 추후 랜덤으로 뿌려줘야할듯?
     */
    @Override
    public List<RestaurantJpo> find(Pageable pageable) {
        return jpaQueryFactory.selectFrom(restaurantJpo)
                //.orderBy(NumberExpression.random().asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    /**
     * 식당 상세 정보
     */
    @Override
    public Optional<RestaurantJpo> find(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(restaurantJpo)
                .where(restaurantJpo.id.eq(id))
                .fetchOne()
        );
    }



}
