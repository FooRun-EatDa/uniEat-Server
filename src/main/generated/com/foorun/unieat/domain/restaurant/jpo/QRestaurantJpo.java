package com.foorun.unieat.domain.restaurant.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRestaurantJpo is a Querydsl query type for RestaurantJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestaurantJpo extends EntityPathBase<RestaurantJpo> {

    private static final long serialVersionUID = -1782477226L;

    public static final QRestaurantJpo restaurantJpo = new QRestaurantJpo("restaurantJpo");

    public QRestaurantJpo(String variable) {
        super(RestaurantJpo.class, forVariable(variable));
    }

    public QRestaurantJpo(Path<? extends RestaurantJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurantJpo(PathMetadata metadata) {
        super(RestaurantJpo.class, metadata);
    }

}

