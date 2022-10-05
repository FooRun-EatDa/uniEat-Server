package com.foorun.unieat.admin.repository;

import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.foorun.unieat.domain.hashtag.jpo.QHashTagRestaurantJpo.hashTagRestaurantJpo;
import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo.restaurantJpo;
import static com.foorun.unieat.util.QuerydslUtil.optAnd;

@Repository
@RequiredArgsConstructor
public class ARestaurantQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<RestaurantJpo> find(Query query) {
        JPAQuery<RestaurantJpo> jpaQuery = jpaQueryFactory.selectFrom(restaurantJpo)
                .leftJoin(restaurantJpo.category)
                .fetchJoin()
                .leftJoin(restaurantJpo.hashTagRestaurants, hashTagRestaurantJpo)
                .where(optAnd(query.keyword, () -> restaurantJpo.name.like("%" + query.keyword + "%")))
                .where(optAnd(query.categories, () -> restaurantJpo.category.id.in(query.categories)))
                .where(optAnd(query.hashTags, () -> hashTagRestaurantJpo.hashTag.content.in(query.hashTags)))
                .groupBy(restaurantJpo.id);
        return new PageImpl<>(jpaQuery
                .offset(query.paging.getOffset())
                .limit(query.paging.getPageSize())
                .fetch(), query.paging, jpaQuery.fetchCount());
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Query {
        private String keyword;
        private Paging paging;

        @Builder.Default
        private List<String> hashTags = new ArrayList<>();

        @Builder.Default
        private List<Long> categories = new ArrayList<>();
    }
}
