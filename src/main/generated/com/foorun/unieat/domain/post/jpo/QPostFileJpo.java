package com.foorun.unieat.domain.post.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostFileJpo is a Querydsl query type for PostFileJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPostFileJpo extends EntityPathBase<PostFileJpo> {

    private static final long serialVersionUID = -750404390L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostFileJpo postFileJpo = new QPostFileJpo("postFileJpo");

    public final com.foorun.unieat.domain.file.jpo.QFileJpo file;

    public final QPostJpo post;

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public final BooleanPath thumbnail = createBoolean("thumbnail");

    public QPostFileJpo(String variable) {
        this(PostFileJpo.class, forVariable(variable), INITS);
    }

    public QPostFileJpo(Path<? extends PostFileJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostFileJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostFileJpo(PathMetadata metadata, PathInits inits) {
        this(PostFileJpo.class, metadata, inits);
    }

    public QPostFileJpo(Class<? extends PostFileJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new com.foorun.unieat.domain.file.jpo.QFileJpo(forProperty("file")) : null;
        this.post = inits.isInitialized("post") ? new QPostJpo(forProperty("post"), inits.get("post")) : null;
    }

}

