package com.foorun.unieat.domain.feeling.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurantFeelingJpo is a Querydsl query type for RestaurantFeelingJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestaurantFeelingJpo extends EntityPathBase<RestaurantFeelingJpo> {

    private static final long serialVersionUID = 1607173089L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurantFeelingJpo restaurantFeelingJpo = new QRestaurantFeelingJpo("restaurantFeelingJpo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public final com.foorun.unieat.domain.review.jpo.QReviewJpo review;

    public QRestaurantFeelingJpo(String variable) {
        this(RestaurantFeelingJpo.class, forVariable(variable), INITS);
    }

    public QRestaurantFeelingJpo(Path<? extends RestaurantFeelingJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurantFeelingJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurantFeelingJpo(PathMetadata metadata, PathInits inits) {
        this(RestaurantFeelingJpo.class, metadata, inits);
    }

    public QRestaurantFeelingJpo(Class<? extends RestaurantFeelingJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
        this.review = inits.isInitialized("review") ? new com.foorun.unieat.domain.review.jpo.QReviewJpo(forProperty("review"), inits.get("review")) : null;
    }

}

