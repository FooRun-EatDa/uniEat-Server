package com.foorun.unieat.domain.bookmark.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookmarkJpo is a Querydsl query type for BookmarkJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookmarkJpo extends EntityPathBase<BookmarkJpo> {

    private static final long serialVersionUID = -1463029706L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookmarkJpo bookmarkJpo = new QBookmarkJpo("bookmarkJpo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo member;

    public final com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo restaurant;

    public QBookmarkJpo(String variable) {
        this(BookmarkJpo.class, forVariable(variable), INITS);
    }

    public QBookmarkJpo(Path<? extends BookmarkJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookmarkJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookmarkJpo(PathMetadata metadata, PathInits inits) {
        this(BookmarkJpo.class, metadata, inits);
    }

    public QBookmarkJpo(Class<? extends BookmarkJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("member"), inits.get("member")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

