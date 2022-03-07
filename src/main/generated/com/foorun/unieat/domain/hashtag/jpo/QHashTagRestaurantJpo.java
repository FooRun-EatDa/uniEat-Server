package com.foorun.unieat.domain.hashtag.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashTagRestaurantJpo is a Querydsl query type for HashTagRestaurantJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHashTagRestaurantJpo extends EntityPathBase<HashTagRestaurantJpo> {

    private static final long serialVersionUID = 632858823L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashTagRestaurantJpo hashTagRestaurantJpo = new QHashTagRestaurantJpo("hashTagRestaurantJpo");

    public final QHashTagJpo hashTag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo restaurant;

    public QHashTagRestaurantJpo(String variable) {
        this(HashTagRestaurantJpo.class, forVariable(variable), INITS);
    }

    public QHashTagRestaurantJpo(Path<? extends HashTagRestaurantJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashTagRestaurantJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashTagRestaurantJpo(PathMetadata metadata, PathInits inits) {
        this(HashTagRestaurantJpo.class, metadata, inits);
    }

    public QHashTagRestaurantJpo(Class<? extends HashTagRestaurantJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashTag = inits.isInitialized("hashTag") ? new QHashTagJpo(forProperty("hashTag")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

