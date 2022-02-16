package com.foorun.unieat.domain.hashtag.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashTagJpo is a Querydsl query type for HashTagJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHashTagJpo extends EntityPathBase<HashTagJpo> {

    private static final long serialVersionUID = 538343172L;

    public static final QHashTagJpo hashTagJpo = new QHashTagJpo("hashTagJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<HashTagRestaurantJpo, QHashTagRestaurantJpo> hashTagRestaurant = this.<HashTagRestaurantJpo, QHashTagRestaurantJpo>createList("hashTagRestaurant", HashTagRestaurantJpo.class, QHashTagRestaurantJpo.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QHashTagJpo(String variable) {
        super(HashTagJpo.class, forVariable(variable));
    }

    public QHashTagJpo(Path<? extends HashTagJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashTagJpo(PathMetadata metadata) {
        super(HashTagJpo.class, metadata);
    }

}

