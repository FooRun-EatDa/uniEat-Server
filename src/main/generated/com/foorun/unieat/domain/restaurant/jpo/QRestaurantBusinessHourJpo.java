package com.foorun.unieat.domain.restaurant.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurantBusinessHourJpo is a Querydsl query type for RestaurantBusinessHourJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestaurantBusinessHourJpo extends EntityPathBase<RestaurantBusinessHourJpo> {

    private static final long serialVersionUID = -1959327118L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurantBusinessHourJpo restaurantBusinessHourJpo = new QRestaurantBusinessHourJpo("restaurantBusinessHourJpo");

    public final QRestaurantBusinessHourJpo_Identify id;

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public QRestaurantBusinessHourJpo(String variable) {
        this(RestaurantBusinessHourJpo.class, forVariable(variable), INITS);
    }

    public QRestaurantBusinessHourJpo(Path<? extends RestaurantBusinessHourJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurantBusinessHourJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurantBusinessHourJpo(PathMetadata metadata, PathInits inits) {
        this(RestaurantBusinessHourJpo.class, metadata, inits);
    }

    public QRestaurantBusinessHourJpo(Class<? extends RestaurantBusinessHourJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QRestaurantBusinessHourJpo_Identify(forProperty("id"), inits.get("id")) : null;
    }

}

