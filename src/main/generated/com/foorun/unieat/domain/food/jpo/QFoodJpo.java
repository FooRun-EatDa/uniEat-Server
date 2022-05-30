package com.foorun.unieat.domain.food.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodJpo is a Querydsl query type for FoodJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFoodJpo extends EntityPathBase<FoodJpo> {

    private static final long serialVersionUID = 759286966L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodJpo foodJpo = new QFoodJpo("foodJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<FoodFileJpo, QFoodFileJpo> files = this.<FoodFileJpo, QFoodFileJpo>createList("files", FoodFileJpo.class, QFoodFileJpo.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo restaurant;

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public final EnumPath<com.foorun.unieat.domain.common.StatusType> status = createEnum("status", com.foorun.unieat.domain.common.StatusType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFoodJpo(String variable) {
        this(FoodJpo.class, forVariable(variable), INITS);
    }

    public QFoodJpo(Path<? extends FoodJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodJpo(PathMetadata metadata, PathInits inits) {
        this(FoodJpo.class, metadata, inits);
    }

    public QFoodJpo(Class<? extends FoodJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

