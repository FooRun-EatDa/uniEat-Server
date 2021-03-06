package com.foorun.unieat.domain.post.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostJpo is a Querydsl query type for PostJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPostJpo extends EntityPathBase<PostJpo> {

    private static final long serialVersionUID = 2111437430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostJpo postJpo = new QPostJpo("postJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final StringPath category = createString("category");

    public final SetPath<com.foorun.unieat.domain.comment.jpo.CommentJpo, com.foorun.unieat.domain.comment.jpo.QCommentJpo> comments = this.<com.foorun.unieat.domain.comment.jpo.CommentJpo, com.foorun.unieat.domain.comment.jpo.QCommentJpo>createSet("comments", com.foorun.unieat.domain.comment.jpo.CommentJpo.class, com.foorun.unieat.domain.comment.jpo.QCommentJpo.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public final StringPath status = createString("status");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPostJpo(String variable) {
        this(PostJpo.class, forVariable(variable), INITS);
    }

    public QPostJpo(Path<? extends PostJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostJpo(PathMetadata metadata, PathInits inits) {
        this(PostJpo.class, metadata, inits);
    }

    public QPostJpo(Class<? extends PostJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
    }

}

