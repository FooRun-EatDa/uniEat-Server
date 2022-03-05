package com.foorun.unieat.domain.member.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberJpo is a Querydsl query type for MemberJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberJpo extends EntityPathBase<MemberJpo> {

    private static final long serialVersionUID = 353579894L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberJpo memberJpo = new QMemberJpo("memberJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final BooleanPath agreeEventLetter = createBoolean("agreeEventLetter");

    public final BooleanPath agreeTerms = createBoolean("agreeTerms");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> dateOfBirth = createNumber("dateOfBirth", Integer.class);

    public final StringPath email = createString("email");

    public final ComparablePath<Character> gender = createComparable("gender", Character.class);

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> latestSignInAt = createDateTime("latestSignInAt", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<com.foorun.unieat.domain.feeling.post.jpo.PostFeelingJpo, com.foorun.unieat.domain.feeling.post.jpo.QPostFeelingJpo> postFeelings = this.<com.foorun.unieat.domain.feeling.post.jpo.PostFeelingJpo, com.foorun.unieat.domain.feeling.post.jpo.QPostFeelingJpo>createList("postFeelings", com.foorun.unieat.domain.feeling.post.jpo.PostFeelingJpo.class, com.foorun.unieat.domain.feeling.post.jpo.QPostFeelingJpo.class, PathInits.DIRECT2);

    public final StringPath profile = createString("profile");

    public final SetPath<com.foorun.unieat.domain.feeling.jpo.RestaurantFeelingJpo, com.foorun.unieat.domain.feeling.jpo.QRestaurantFeelingJpo> restaurantFeelings = this.<com.foorun.unieat.domain.feeling.jpo.RestaurantFeelingJpo, com.foorun.unieat.domain.feeling.jpo.QRestaurantFeelingJpo>createSet("restaurantFeelings", com.foorun.unieat.domain.feeling.jpo.RestaurantFeelingJpo.class, com.foorun.unieat.domain.feeling.jpo.QRestaurantFeelingJpo.class, PathInits.DIRECT2);

    public final SetPath<com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo> reviewFeelings = this.<com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo>createSet("reviewFeelings", com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo.class, com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo.class, PathInits.DIRECT2);

<<<<<<< HEAD
=======
    public final EnumPath<com.foorun.unieat.domain.member.Role> role = createEnum("role", com.foorun.unieat.domain.member.Role.class);

>>>>>>> develop
    public final com.foorun.unieat.domain.school.jpo.QSchoolJpo school;

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberJpo(String variable) {
        this(MemberJpo.class, forVariable(variable), INITS);
    }

    public QMemberJpo(Path<? extends MemberJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberJpo(PathMetadata metadata, PathInits inits) {
        this(MemberJpo.class, metadata, inits);
    }

    public QMemberJpo(Class<? extends MemberJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.school = inits.isInitialized("school") ? new com.foorun.unieat.domain.school.jpo.QSchoolJpo(forProperty("school"), inits.get("school")) : null;
    }

}

