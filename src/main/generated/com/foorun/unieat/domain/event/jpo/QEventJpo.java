package com.foorun.unieat.domain.event.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventJpo is a Querydsl query type for EventJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEventJpo extends EntityPathBase<EventJpo> {

    private static final long serialVersionUID = -723149048L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventJpo eventJpo = new QEventJpo("eventJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final NumberPath<Long> couponCount = createNumber("couponCount", Long.class);

    public final SetPath<com.foorun.unieat.domain.coupon.entity.CouponJpo, com.foorun.unieat.domain.coupon.entity.QCouponJpo> coupons = this.<com.foorun.unieat.domain.coupon.entity.CouponJpo, com.foorun.unieat.domain.coupon.entity.QCouponJpo>createSet("coupons", com.foorun.unieat.domain.coupon.entity.CouponJpo.class, com.foorun.unieat.domain.coupon.entity.QCouponJpo.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath desc = createString("desc");

    public final StringPath expiredDate = createString("expiredDate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath name = createString("name");

    public final ListPath<String, StringPath> notice = this.<String, StringPath>createList("notice", String.class, StringPath.class, PathInits.DIRECT2);

    public final com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo restaurant;

    public final EnumPath<com.foorun.unieat.domain.event.EventStatus> status = createEnum("status", com.foorun.unieat.domain.event.EventStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QEventJpo(String variable) {
        this(EventJpo.class, forVariable(variable), INITS);
    }

    public QEventJpo(Path<? extends EventJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventJpo(PathMetadata metadata, PathInits inits) {
        this(EventJpo.class, metadata, inits);
    }

    public QEventJpo(Class<? extends EventJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

