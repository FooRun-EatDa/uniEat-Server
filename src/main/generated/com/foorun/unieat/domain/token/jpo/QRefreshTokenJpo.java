package com.foorun.unieat.domain.token.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRefreshTokenJpo is a Querydsl query type for RefreshTokenJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRefreshTokenJpo extends EntityPathBase<RefreshTokenJpo> {

    private static final long serialVersionUID = -1805222191L;

    public static final QRefreshTokenJpo refreshTokenJpo = new QRefreshTokenJpo("refreshTokenJpo");

    public final StringPath token = createString("token");

    public QRefreshTokenJpo(String variable) {
        super(RefreshTokenJpo.class, forVariable(variable));
    }

    public QRefreshTokenJpo(Path<? extends RefreshTokenJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRefreshTokenJpo(PathMetadata metadata) {
        super(RefreshTokenJpo.class, metadata);
    }

}

