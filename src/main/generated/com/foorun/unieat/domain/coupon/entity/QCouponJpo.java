package com.foorun.unieat.domain.coupon.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCouponJpo is a Querydsl query type for CouponJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCouponJpo extends EntityPathBase<CouponJpo> {

    private static final long serialVersionUID = -695278208L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCouponJpo couponJpo = new QCouponJpo("couponJpo");

    public final com.foorun.unieat.domain.event.jpo.QEventJpo event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public QCouponJpo(String variable) {
        this(CouponJpo.class, forVariable(variable), INITS);
    }

    public QCouponJpo(Path<? extends CouponJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCouponJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCouponJpo(PathMetadata metadata, PathInits inits) {
        this(CouponJpo.class, metadata, inits);
    }

    public QCouponJpo(Class<? extends CouponJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new com.foorun.unieat.domain.event.jpo.QEventJpo(forProperty("event"), inits.get("event")) : null;
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
    }

}

