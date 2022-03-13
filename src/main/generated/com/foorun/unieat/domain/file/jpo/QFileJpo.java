package com.foorun.unieat.domain.file.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileJpo is a Querydsl query type for FileJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileJpo extends EntityPathBase<FileJpo> {

    private static final long serialVersionUID = 1352667894L;

    public static final QFileJpo fileJpo = new QFileJpo("fileJpo");

    public final NumberPath<Long> bytes = createNumber("bytes", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath format = createString("format");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final EnumPath<com.foorun.unieat.domain.common.StatusType> status = createEnum("status", com.foorun.unieat.domain.common.StatusType.class);

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QFileJpo(String variable) {
        super(FileJpo.class, forVariable(variable));
    }

    public QFileJpo(Path<? extends FileJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileJpo(PathMetadata metadata) {
        super(FileJpo.class, metadata);
    }

}

