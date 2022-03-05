package com.foorun.unieat.domain.category.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryJpo is a Querydsl query type for CategoryJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategoryJpo extends EntityPathBase<CategoryJpo> {

    private static final long serialVersionUID = -1352471754L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryJpo categoryJpo = new QCategoryJpo("categoryJpo");

<<<<<<< HEAD
    public final StringPath categoryName = createString("categoryName");
=======
    public final StringPath category_name = createString("category_name");
>>>>>>> develop

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo restaurant;

    public QCategoryJpo(String variable) {
        this(CategoryJpo.class, forVariable(variable), INITS);
    }

    public QCategoryJpo(Path<? extends CategoryJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryJpo(PathMetadata metadata, PathInits inits) {
        this(CategoryJpo.class, metadata, inits);
    }

    public QCategoryJpo(Class<? extends CategoryJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

