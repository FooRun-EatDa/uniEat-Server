package com.foorun.unieat.domain.comment.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentJpo is a Querydsl query type for CommentJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommentJpo extends EntityPathBase<CommentJpo> {

    private static final long serialVersionUID = -601694082L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentJpo commentJpo = new QCommentJpo("commentJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final SetPath<CommentJpo, QCommentJpo> comments = this.<CommentJpo, QCommentJpo>createSet("comments", CommentJpo.class, QCommentJpo.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QCommentJpo parent;

    public final com.foorun.unieat.domain.post.jpo.QPostJpo post;

    public final BooleanPath secret = createBoolean("secret");

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCommentJpo(String variable) {
        this(CommentJpo.class, forVariable(variable), INITS);
    }

    public QCommentJpo(Path<? extends CommentJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentJpo(PathMetadata metadata, PathInits inits) {
        this(CommentJpo.class, metadata, inits);
    }

    public QCommentJpo(Class<? extends CommentJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QCommentJpo(forProperty("parent"), inits.get("parent")) : null;
        this.post = inits.isInitialized("post") ? new com.foorun.unieat.domain.post.jpo.QPostJpo(forProperty("post"), inits.get("post")) : null;
    }

}

