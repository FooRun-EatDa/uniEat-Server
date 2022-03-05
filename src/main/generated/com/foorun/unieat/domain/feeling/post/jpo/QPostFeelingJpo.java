package com.foorun.unieat.domain.feeling.post.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostFeelingJpo is a Querydsl query type for PostFeelingJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPostFeelingJpo extends EntityPathBase<PostFeelingJpo> {

    private static final long serialVersionUID = -1723477202L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostFeelingJpo postFeelingJpo = new QPostFeelingJpo("postFeelingJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public final com.foorun.unieat.domain.post.jpo.QPostJpo post;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPostFeelingJpo(String variable) {
        this(PostFeelingJpo.class, forVariable(variable), INITS);
    }

    public QPostFeelingJpo(Path<? extends PostFeelingJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostFeelingJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostFeelingJpo(PathMetadata metadata, PathInits inits) {
        this(PostFeelingJpo.class, metadata, inits);
    }

    public QPostFeelingJpo(Class<? extends PostFeelingJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
        this.post = inits.isInitialized("post") ? new com.foorun.unieat.domain.post.jpo.QPostJpo(forProperty("post"), inits.get("post")) : null;
    }

}

