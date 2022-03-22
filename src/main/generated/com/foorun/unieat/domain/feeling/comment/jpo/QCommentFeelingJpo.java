package com.foorun.unieat.domain.feeling.comment.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentFeelingJpo is a Querydsl query type for CommentFeelingJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommentFeelingJpo extends EntityPathBase<CommentFeelingJpo> {

    private static final long serialVersionUID = 1367110054L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentFeelingJpo commentFeelingJpo = new QCommentFeelingJpo("commentFeelingJpo");

    public final com.foorun.unieat.domain.comment.jpo.QCommentJpo comment;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public QCommentFeelingJpo(String variable) {
        this(CommentFeelingJpo.class, forVariable(variable), INITS);
    }

    public QCommentFeelingJpo(Path<? extends CommentFeelingJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentFeelingJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentFeelingJpo(PathMetadata metadata, PathInits inits) {
        this(CommentFeelingJpo.class, metadata, inits);
    }

    public QCommentFeelingJpo(Class<? extends CommentFeelingJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new com.foorun.unieat.domain.comment.jpo.QCommentJpo(forProperty("comment"), inits.get("comment")) : null;
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
    }

}

