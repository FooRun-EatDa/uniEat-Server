package com.foorun.unieat.domain.restaurant.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurantJpo is a Querydsl query type for RestaurantJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestaurantJpo extends EntityPathBase<RestaurantJpo> {

    private static final long serialVersionUID = -1782477226L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurantJpo restaurantJpo = new QRestaurantJpo("restaurantJpo");

    public final StringPath address = createString("address");

    public final com.foorun.unieat.domain.bookmark.jpo.QBookmarkJpo bookmark;

    public final SetPath<com.foorun.unieat.domain.category.jpo.CategoryJpo, com.foorun.unieat.domain.category.jpo.QCategoryJpo> categorys = this.<com.foorun.unieat.domain.category.jpo.CategoryJpo, com.foorun.unieat.domain.category.jpo.QCategoryJpo>createSet("categorys", com.foorun.unieat.domain.category.jpo.CategoryJpo.class, com.foorun.unieat.domain.category.jpo.QCategoryJpo.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final StringPath district = createString("district");

    public final StringPath explanation = createString("explanation");

    public final SetPath<com.foorun.unieat.domain.food.jpo.FoodJpo, com.foorun.unieat.domain.food.jpo.QFoodJpo> foods = this.<com.foorun.unieat.domain.food.jpo.FoodJpo, com.foorun.unieat.domain.food.jpo.QFoodJpo>createSet("foods", com.foorun.unieat.domain.food.jpo.FoodJpo.class, com.foorun.unieat.domain.food.jpo.QFoodJpo.class, PathInits.DIRECT2);

    public final SetPath<com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo, com.foorun.unieat.domain.hashtag.jpo.QHashTagRestaurantJpo> hashTagRestaurants = this.<com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo, com.foorun.unieat.domain.hashtag.jpo.QHashTagRestaurantJpo>createSet("hashTagRestaurants", com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo.class, com.foorun.unieat.domain.hashtag.jpo.QHashTagRestaurantJpo.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imguUrl = createString("imguUrl");

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final StringPath name = createString("name");

    public final StringPath operationTime = createString("operationTime");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.foorun.unieat.domain.code.region.jpo.QRegionCodeJpo regionCode;

    public final SetPath<com.foorun.unieat.domain.review.jpo.ReviewJpo, com.foorun.unieat.domain.review.jpo.QReviewJpo> reviews = this.<com.foorun.unieat.domain.review.jpo.ReviewJpo, com.foorun.unieat.domain.review.jpo.QReviewJpo>createSet("reviews", com.foorun.unieat.domain.review.jpo.ReviewJpo.class, com.foorun.unieat.domain.review.jpo.QReviewJpo.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public QRestaurantJpo(String variable) {
        this(RestaurantJpo.class, forVariable(variable), INITS);
    }

    public QRestaurantJpo(Path<? extends RestaurantJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurantJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurantJpo(PathMetadata metadata, PathInits inits) {
        this(RestaurantJpo.class, metadata, inits);
    }

    public QRestaurantJpo(Class<? extends RestaurantJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bookmark = inits.isInitialized("bookmark") ? new com.foorun.unieat.domain.bookmark.jpo.QBookmarkJpo(forProperty("bookmark"), inits.get("bookmark")) : null;
        this.regionCode = inits.isInitialized("regionCode") ? new com.foorun.unieat.domain.code.region.jpo.QRegionCodeJpo(forProperty("regionCode"), inits.get("regionCode")) : null;
    }

}

