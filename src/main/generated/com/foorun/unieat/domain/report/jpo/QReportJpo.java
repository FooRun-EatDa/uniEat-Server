package com.foorun.unieat.domain.report.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportJpo is a Querydsl query type for ReportJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReportJpo extends EntityPathBase<ReportJpo> {

    private static final long serialVersionUID = -996057162L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportJpo reportJpo = new QReportJpo("reportJpo");

    public final com.foorun.unieat.domain.QBaseTimeJpo _super = new com.foorun.unieat.domain.QBaseTimeJpo(this);

    public final SimplePath<com.foorun.unieat.domain.report.dto.Report> content = createSimple("content", com.foorun.unieat.domain.report.dto.Report.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo fromMember;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.foorun.unieat.domain.report.ReportType> reportType = createEnum("reportType", com.foorun.unieat.domain.report.ReportType.class);

    public final com.foorun.unieat.domain.member.jpo.QMemberJpo toMember;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReportJpo(String variable) {
        this(ReportJpo.class, forVariable(variable), INITS);
    }

    public QReportJpo(Path<? extends ReportJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportJpo(PathMetadata metadata, PathInits inits) {
        this(ReportJpo.class, metadata, inits);
    }

    public QReportJpo(Class<? extends ReportJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromMember = inits.isInitialized("fromMember") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("fromMember"), inits.get("fromMember")) : null;
        this.toMember = inits.isInitialized("toMember") ? new com.foorun.unieat.domain.member.jpo.QMemberJpo(forProperty("toMember"), inits.get("toMember")) : null;
    }

}

