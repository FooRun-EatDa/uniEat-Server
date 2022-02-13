package com.foorun.unieat.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseTimeJpo is a Querydsl query type for BaseTimeJpo
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseTimeJpo extends EntityPathBase<BaseTimeJpo> {

    private static final long serialVersionUID = 399924459L;

    public static final QBaseTimeJpo baseTimeJpo = new QBaseTimeJpo("baseTimeJpo");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QBaseTimeJpo(String variable) {
        super(BaseTimeJpo.class, forVariable(variable));
    }

    public QBaseTimeJpo(Path<? extends BaseTimeJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTimeJpo(PathMetadata metadata) {
        super(BaseTimeJpo.class, metadata);
    }

}

