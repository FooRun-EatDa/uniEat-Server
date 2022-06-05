package com.foorun.unieat.domain.restaurant.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurantFileJpo is a Querydsl query type for RestaurantFileJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestaurantFileJpo extends EntityPathBase<RestaurantFileJpo> {

    private static final long serialVersionUID = -1014996806L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurantFileJpo restaurantFileJpo = new QRestaurantFileJpo("restaurantFileJpo");

    public final com.foorun.unieat.domain.file.jpo.QBaseFileJpo _super;

    // inherited
    public final com.foorun.unieat.domain.file.jpo.QFileJpo file;

    public final QRestaurantJpo restaurant;

    //inherited
    public final NumberPath<Integer> sequence;

    //inherited
    public final BooleanPath thumbnail;

    public QRestaurantFileJpo(String variable) {
        this(RestaurantFileJpo.class, forVariable(variable), INITS);
    }

    public QRestaurantFileJpo(Path<? extends RestaurantFileJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurantFileJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurantFileJpo(PathMetadata metadata, PathInits inits) {
        this(RestaurantFileJpo.class, metadata, inits);
    }

    public QRestaurantFileJpo(Class<? extends RestaurantFileJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.foorun.unieat.domain.file.jpo.QBaseFileJpo(type, metadata, inits);
        this.file = _super.file;
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
        this.sequence = _super.sequence;
        this.thumbnail = _super.thumbnail;
    }

}

