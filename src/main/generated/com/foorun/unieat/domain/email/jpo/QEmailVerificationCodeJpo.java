package com.foorun.unieat.domain.email.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailVerificationCodeJpo is a Querydsl query type for EmailVerificationCodeJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmailVerificationCodeJpo extends EntityPathBase<EmailVerificationCodeJpo> {

    private static final long serialVersionUID = -1235220676L;

    public static final QEmailVerificationCodeJpo emailVerificationCodeJpo = new QEmailVerificationCodeJpo("emailVerificationCodeJpo");

    public final StringPath code = createString("code");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public QEmailVerificationCodeJpo(String variable) {
        super(EmailVerificationCodeJpo.class, forVariable(variable));
    }

    public QEmailVerificationCodeJpo(Path<? extends EmailVerificationCodeJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailVerificationCodeJpo(PathMetadata metadata) {
        super(EmailVerificationCodeJpo.class, metadata);
    }

}

