package com.foorun.unieat.domain.history.member.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHistoryMemberSignInJpo is a Querydsl query type for HistoryMemberSignInJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHistoryMemberSignInJpo extends EntityPathBase<HistoryMemberSignInJpo> {

    private static final long serialVersionUID = -1664304104L;

    public static final QHistoryMemberSignInJpo historyMemberSignInJpo = new QHistoryMemberSignInJpo("historyMemberSignInJpo");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public QHistoryMemberSignInJpo(String variable) {
        super(HistoryMemberSignInJpo.class, forVariable(variable));
    }

    public QHistoryMemberSignInJpo(Path<? extends HistoryMemberSignInJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHistoryMemberSignInJpo(PathMetadata metadata) {
        super(HistoryMemberSignInJpo.class, metadata);
    }

}

