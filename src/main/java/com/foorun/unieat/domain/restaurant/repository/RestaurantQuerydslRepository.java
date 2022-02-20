package com.foorun.unieat.domain.restaurant.repository;


import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;

import com.foorun.unieat.domain.category.dto.Category;
import com.foorun.unieat.domain.category.jpo.QCategoryJpo;
import com.foorun.unieat.domain.restaurant.Prices;
import com.foorun.unieat.domain.restaurant.dto.FilteringRestaurant;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.category.jpo.QCategoryJpo.categoryJpo;
import static com.foorun.unieat.domain.hashtag.jpo.QHashTagRestaurantJpo.hashTagRestaurantJpo;
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


    //3개 테이블 조인 -> 효율성?
    public List<RestaurantJpo> findByFilter(FilteringRestaurant filtering, Pageable pageable) {
        return jpaQueryFactory.select(restaurantJpo)
                .join(restaurantJpo.hashTagRestaurants, hashTagRestaurantJpo).on(
                        hashTagChecking(filtering.getHashTags())
                )
                .join(restaurantJpo.categorys, categoryJpo).on(
                        categoryChecking(filtering.getCategories())
                )
                .where(
                        priceChecking(filtering.getPrices())
                        ,regionChecking(filtering.getRegions())
                ).offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }


    private BooleanBuilder hashTagChecking(List<String> hashTag){
        BooleanBuilder builder = new BooleanBuilder();
        for (String s : hashTag) {
            builder.and(hashTagRestaurantJpo.hashTag.content.eq(s)); // hastag 내용이 필터에 담긴 해시태그와 같을 경우에만 return
        }
        return builder;
    }

    private BooleanBuilder categoryChecking(List<String> category){
        BooleanBuilder builder = new BooleanBuilder();
        for (String s : category) {
            builder.and(categoryJpo.categoryName.eq(s));
        }

        return builder;
    }


    private BooleanBuilder priceChecking(List<Integer> prices){
        BooleanBuilder builder = new BooleanBuilder();
        for ( Integer i : prices) {
            Prices price = Prices.getPricesByLevel(i);
            builder.and(restaurantJpo.price.between(price.getLowerBound(),price.getUpperBound()));
        }

         return builder;
    }


    /**
     *
     * 학생들이 부르는 지역
     * 미완성
     */
    private BooleanExpression regionChecking(List<String> region){

//        return restaurantJpo.regionCode.eq(region);
        return null;
    }




}
