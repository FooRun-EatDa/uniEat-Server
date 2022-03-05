package com.foorun.unieat.domain.food.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodJpo is a Querydsl query type for FoodJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFoodJpo extends EntityPathBase<FoodJpo> {

    private static final long serialVersionUID = 759286966L;

    public static final QFoodJpo foodJpo = new QFoodJpo("foodJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

<<<<<<< HEAD
    public final StringPath imgUrl = createString("imgUrl");
=======
    public final StringPath img_url = createString("img_url");
>>>>>>> develop

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFoodJpo(String variable) {
        super(FoodJpo.class, forVariable(variable));
    }

    public QFoodJpo(Path<? extends FoodJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodJpo(PathMetadata metadata) {
        super(FoodJpo.class, metadata);
    }

}

