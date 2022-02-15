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

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> dateOfBirth = createNumber("dateOfBirth", Integer.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profile = createString("profile");

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

