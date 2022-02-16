package com.foorun.unieat.domain.feeling.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewFeelingJpo is a Querydsl query type for ReviewFeelingJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReviewFeelingJpo extends EntityPathBase<ReviewFeelingJpo> {

    private static final long serialVersionUID = -550888004L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewFeelingJpo reviewFeelingJpo = new QReviewFeelingJpo("reviewFeelingJpo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public final com.foorun.unieat.domain.review.jpo.QReviewJpo review;

    public QReviewFeelingJpo(String variable) {
        this(ReviewFeelingJpo.class, forVariable(variable), INITS);
    }

    public QReviewFeelingJpo(Path<? extends ReviewFeelingJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewFeelingJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewFeelingJpo(PathMetadata metadata, PathInits inits) {
        this(ReviewFeelingJpo.class, metadata, inits);
    }

    public QReviewFeelingJpo(Class<? extends ReviewFeelingJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
        this.review = inits.isInitialized("review") ? new com.foorun.unieat.domain.review.jpo.QReviewJpo(forProperty("review"), inits.get("review")) : null;
    }

}

