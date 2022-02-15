package com.foorun.unieat.domain.code.region.jpo.embed;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoordinateJpo is a Querydsl query type for CoordinateJpo
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QCoordinateJpo extends BeanPath<CoordinateJpo> {

    private static final long serialVersionUID = -1362149954L;

    public static final QCoordinateJpo coordinateJpo = new QCoordinateJpo("coordinateJpo");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public QCoordinateJpo(String variable) {
        super(CoordinateJpo.class, forVariable(variable));
    }

    public QCoordinateJpo(Path<? extends CoordinateJpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoordinateJpo(PathMetadata metadata) {
        super(CoordinateJpo.class, metadata);
    }

}

