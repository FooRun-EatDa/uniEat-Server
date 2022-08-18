package com.foorun.unieat.domain.event;

import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.event.jpo.QEventJpo;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.event.jpo.QEventJpo.*;
import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo.restaurantJpo;

@Repository
@RequiredArgsConstructor
public class EventQuerydslRepository implements QuerydslSelectMulti<EventJpo>, QuerydslSelectSingle<EventJpo,Long> {


    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<EventJpo> find(Pageable pageable) {
        return jpaQueryFactory.selectFrom(eventJpo)
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Optional<EventJpo> find(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(eventJpo)
                .where(eventJpo.id.eq(id))
                .fetchOne()
        );
    }

    public boolean isEventRestaurant(Long storeId){
        return jpaQueryFactory.selectOne().from(eventJpo)
                .where(eventJpo.restaurant.id.eq(storeId))
                .fetchFirst() != null;
    }


}
