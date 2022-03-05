package com.foorun.unieat.domain.review.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewJpo is a Querydsl query type for ReviewJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReviewJpo extends EntityPathBase<ReviewJpo> {

    private static final long serialVersionUID = 1202581046L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewJpo reviewJpo = new QReviewJpo("reviewJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

<<<<<<< HEAD
    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public final com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo restaurant;

    public final SetPath<com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo> reviewFeelings = this.<com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo>createSet("reviewFeelings", com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo.class, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo.class, PathInits.DIRECT2);

    public final NumberPath<Integer> starScore = createNumber("starScore", Integer.class);
=======
    public final com.foorun.unieat.domain.member.jpo.QMemberJpo memberJpo;

    public final com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo restaurant;

    public final SetPath<com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo> reviewFeeling = this.<com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo>createSet("reviewFeeling", com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo.class, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo.class, PathInits.DIRECT2);

    public final NumberPath<Integer> star_score = createNumber("star_score", Integer.class);
>>>>>>> develop

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReviewJpo(String variable) {
        this(ReviewJpo.class, forVariable(variable), INITS);
    }

    public QReviewJpo(Path<? extends ReviewJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewJpo(PathMetadata metadata, PathInits inits) {
        this(ReviewJpo.class, metadata, inits);
    }

    public QReviewJpo(Class<? extends ReviewJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
<<<<<<< HEAD
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
=======
        this.memberJpo = inits.isInitialized("memberJpo") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("memberJpo"), inits.get("memberJpo")) : null;
>>>>>>> develop
        this.restaurant = inits.isInitialized("restaurant") ? new com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

