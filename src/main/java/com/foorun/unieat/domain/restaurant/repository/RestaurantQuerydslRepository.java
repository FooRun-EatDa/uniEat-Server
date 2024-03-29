package com.foorun.unieat.domain.restaurant.repository;


import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.restaurant.Prices;
import com.foorun.unieat.domain.restaurant.dto.FilteringRestaurant;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.category.jpo.QCategoryJpo.categoryJpo;
import static com.foorun.unieat.domain.code.region.jpo.QRegionCodeJpo.regionCodeJpo;
import static com.foorun.unieat.domain.food.jpo.QFoodJpo.foodJpo;
import static com.foorun.unieat.domain.hashtag.jpo.QHashTagRestaurantJpo.hashTagRestaurantJpo;
import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantBestJpo.restaurantBestJpo;
import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo.restaurantJpo;

@Repository
@RequiredArgsConstructor
public class RestaurantQuerydslRepository implements QuerydslSelectMulti<RestaurantJpo>, QuerydslSelectSingle<RestaurantJpo, Long> {


    private final JPAQueryFactory jpaQueryFactory;



    public List<RestaurantJpo> fetchTopRestaurant(){
        return jpaQueryFactory.select(restaurantJpo).from(restaurantJpo).leftJoin(
                restaurantJpo.bestRestaurants,restaurantBestJpo
        ).where(restaurantBestJpo.restaurant.id.eq(restaurantJpo.id)).distinct().fetch();

    }


    public List<RestaurantJpo> find(){
        return jpaQueryFactory.selectFrom(restaurantJpo)
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .distinct()
                .fetch();
    }

    /**
     * 심플 식당 정보 다건 조회,
     * 추후 랜덤으로 뿌려줘야할듯?
     */
    @Override
    public List<RestaurantJpo> find(Pageable pageable) {
        //페이징 처리 안함
        return jpaQueryFactory.selectFrom(restaurantJpo)
                .leftJoin(restaurantJpo.files)
                .fetchJoin()
                .leftJoin(restaurantJpo.reviews)
                .fetchJoin()
                .leftJoin(restaurantJpo.foods)
                .fetchJoin()
                .leftJoin(restaurantJpo.categorys)
                .fetchJoin()
//                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
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
                .from(restaurantJpo)
                .innerJoin(restaurantJpo.hashTagRestaurants, hashTagRestaurantJpo)
                .innerJoin(restaurantJpo.categorys, categoryJpo)
                .where(
                        priceChecking(filtering.getPrices()),
                        hashTagRestaurantJpo.hashTag.content.in(filtering.getHashTags()),
                                categoryJpo.categoryName.in(filtering.getCategories())
                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
                .fetch();

    }





    private BooleanBuilder priceChecking(List<Integer> prices){
        BooleanBuilder builder = new BooleanBuilder();
        for ( Integer i : prices) {
            Prices price = Prices.getPricesByLevel(i);
            builder.or(restaurantJpo.price.between(price.getLowerBound(),price.getUpperBound()));
        }

         return builder;
    }


    /**
     *
     * 학생들이 부르는 지역 매핑 테이블 필요
     * 미완성
     */
    private BooleanExpression regionChecking(List<String> region){

//        return restaurantJpo.regionCode.eq(region);
        return null;
    }


    /**
     * 검색으로 식당 찾기
     */

    public List<RestaurantJpo> findBySearch(String keyWord,Pageable pageable){
        return jpaQueryFactory.select(restaurantJpo)
                .from(restaurantJpo)
                .innerJoin(restaurantJpo.foods, foodJpo)
                .innerJoin(restaurantJpo.regionCode, regionCodeJpo) //지역 추가 필요
                .where(
                        restaurantJpo.name.contains(keyWord)
                                .or(foodJpo.name.contains(keyWord))
                                .or(regionCodeJpo.fullName.contains(keyWord))

                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }





}
