package com.foorun.unieat.domain.restaurant.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurantBusinessHourJpo_Identify is a Querydsl query type for Identify
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QRestaurantBusinessHourJpo_Identify extends BeanPath<RestaurantBusinessHourJpo.Identify> {

    private static final long serialVersionUID = 1775721256L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurantBusinessHourJpo_Identify identify = new QRestaurantBusinessHourJpo_Identify("identify");

    public final StringPath content = createString("content");

    public final QRestaurantJpo restaurant;

    public QRestaurantBusinessHourJpo_Identify(String variable) {
        this(RestaurantBusinessHourJpo.Identify.class, forVariable(variable), INITS);
    }

    public QRestaurantBusinessHourJpo_Identify(Path<? extends RestaurantBusinessHourJpo.Identify> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurantBusinessHourJpo_Identify(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurantBusinessHourJpo_Identify(PathMetadata metadata, PathInits inits) {
        this(RestaurantBusinessHourJpo.Identify.class, metadata, inits);
    }

    public QRestaurantBusinessHourJpo_Identify(Class<? extends RestaurantBusinessHourJpo.Identify> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

