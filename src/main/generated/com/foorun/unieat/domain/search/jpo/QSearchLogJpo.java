package com.foorun.unieat.domain.search.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSearchLogJpo is a Querydsl query type for SearchLogJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSearchLogJpo extends EntityPathBase<SearchLogJpo> {

    private static final long serialVersionUID = -31627624L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSearchLogJpo searchLogJpo = new QSearchLogJpo("searchLogJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public final StringPath searchText = createString("searchText");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSearchLogJpo(String variable) {
        this(SearchLogJpo.class, forVariable(variable), INITS);
    }

    public QSearchLogJpo(Path<? extends SearchLogJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSearchLogJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSearchLogJpo(PathMetadata metadata, PathInits inits) {
        this(SearchLogJpo.class, metadata, inits);
    }

    public QSearchLogJpo(Class<? extends SearchLogJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
    }

}

