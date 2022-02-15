package com.foorun.unieat.domain.school.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchoolJpo is a Querydsl query type for SchoolJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSchoolJpo extends EntityPathBase<SchoolJpo> {

    private static final long serialVersionUID = -1779382346L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchoolJpo schoolJpo = new QSchoolJpo("schoolJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.foorun.unieat.domain.code.region.jpo.QRegionCodeJpo region;

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSchoolJpo(String variable) {
        this(SchoolJpo.class, forVariable(variable), INITS);
    }

    public QSchoolJpo(Path<? extends SchoolJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchoolJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchoolJpo(PathMetadata metadata, PathInits inits) {
        this(SchoolJpo.class, metadata, inits);
    }

    public QSchoolJpo(Class<? extends SchoolJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new com.foorun.unieat.domain.code.region.jpo.QRegionCodeJpo(forProperty("region"), inits.get("region")) : null;
    }

}

