package com.foorun.unieat.domain.restaurant.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurantBestJpo is a Querydsl query type for RestaurantBestJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestaurantBestJpo extends EntityPathBase<RestaurantBestJpo> {

    private static final long serialVersionUID = -377649326L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurantBestJpo restaurantBestJpo = new QRestaurantBestJpo("restaurantBestJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.code.region.jpo.QRegionCodeJpo regionCode;

    public final QRestaurantJpo restaurant;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRestaurantBestJpo(String variable) {
        this(RestaurantBestJpo.class, forVariable(variable), INITS);
    }

    public QRestaurantBestJpo(Path<? extends RestaurantBestJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurantBestJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurantBestJpo(PathMetadata metadata, PathInits inits) {
        this(RestaurantBestJpo.class, metadata, inits);
    }

    public QRestaurantBestJpo(Class<? extends RestaurantBestJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.regionCode = inits.isInitialized("regionCode") ? new com.foorun.unieat.domain.code.region.jpo.QRegionCodeJpo(forProperty("regionCode"), inits.get("regionCode")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

