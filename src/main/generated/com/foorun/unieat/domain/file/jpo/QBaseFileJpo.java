package com.foorun.unieat.domain.file.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseFileJpo is a Querydsl query type for BaseFileJpo
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseFileJpo extends EntityPathBase<BaseFileJpo> {

    private static final long serialVersionUID = 1145850469L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseFileJpo baseFileJpo = new QBaseFileJpo("baseFileJpo");

    public final QFileJpo file;

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public final BooleanPath thumbnail = createBoolean("thumbnail");

    public QBaseFileJpo(String variable) {
        this(BaseFileJpo.class, forVariable(variable), INITS);
    }

    public QBaseFileJpo(Path<? extends BaseFileJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseFileJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBaseFileJpo(PathMetadata metadata, PathInits inits) {
        this(BaseFileJpo.class, metadata, inits);
    }

    public QBaseFileJpo(Class<? extends BaseFileJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new QFileJpo(forProperty("file")) : null;
    }

}

