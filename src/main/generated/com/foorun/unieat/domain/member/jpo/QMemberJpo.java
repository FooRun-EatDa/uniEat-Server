package com.foorun.unieat.domain.member.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberJpo is a Querydsl query type for MemberJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberJpo extends EntityPathBase<MemberJpo> {

    private static final long serialVersionUID = 353579894L;

    public static final QMemberJpo memberJpo = new QMemberJpo("memberJpo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QMemberJpo(String variable) {
        super(MemberJpo.class, forVariable(variable));
    }

    public QMemberJpo(Path<? extends MemberJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberJpo(PathMetadata metadata) {
        super(MemberJpo.class, metadata);
    }

}

