package com.foorun.unieat.domain.code.region.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegionCodeJpo is a Querydsl query type for RegionCodeJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRegionCodeJpo extends EntityPathBase<RegionCodeJpo> {

    private static final long serialVersionUID = 113927018L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegionCodeJpo regionCodeJpo = new QRegionCodeJpo("regionCodeJpo");

    public final SetPath<com.foorun.unieat.domain.restaurant.jpo.RestaurantBestJpo, com.foorun.unieat.domain.restaurant.jpo.QRestaurantBestJpo> bestRestaurants = this.<com.foorun.unieat.domain.restaurant.jpo.RestaurantBestJpo, com.foorun.unieat.domain.restaurant.jpo.QRestaurantBestJpo>createSet("bestRestaurants", com.foorun.unieat.domain.restaurant.jpo.RestaurantBestJpo.class, com.foorun.unieat.domain.restaurant.jpo.QRestaurantBestJpo.class, PathInits.DIRECT2);

    public final com.foorun.unieat.domain.code.region.jpo.embed.QCoordinateJpo coordinate;

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> sido = createNumber("sido", Integer.class);

    public final NumberPath<Integer> sigungu = createNumber("sigungu", Integer.class);

    public final NumberPath<Integer> umd = createNumber("umd", Integer.class);

    public QRegionCodeJpo(String variable) {
        this(RegionCodeJpo.class, forVariable(variable), INITS);
    }

    public QRegionCodeJpo(Path<? extends RegionCodeJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegionCodeJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegionCodeJpo(PathMetadata metadata, PathInits inits) {
        this(RegionCodeJpo.class, metadata, inits);
    }

    public QRegionCodeJpo(Class<? extends RegionCodeJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coordinate = inits.isInitialized("coordinate") ? new com.foorun.unieat.domain.code.region.jpo.embed.QCoordinateJpo(forProperty("coordinate")) : null;
    }

}

