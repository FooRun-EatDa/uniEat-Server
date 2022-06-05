package com.foorun.unieat.domain.food.jpo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodFileJpo is a Querydsl query type for FoodFileJpo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFoodFileJpo extends EntityPathBase<FoodFileJpo> {

    private static final long serialVersionUID = 167407386L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodFileJpo foodFileJpo = new QFoodFileJpo("foodFileJpo");

    public final com.foorun.unieat.domain.file.jpo.QBaseFileJpo _super;

    // inherited
    public final com.foorun.unieat.domain.file.jpo.QFileJpo file;

    public final QFoodJpo food;

    //inherited
    public final NumberPath<Integer> sequence;

    //inherited
    public final BooleanPath thumbnail;

    public QFoodFileJpo(String variable) {
        this(FoodFileJpo.class, forVariable(variable), INITS);
    }

    public QFoodFileJpo(Path<? extends FoodFileJpo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodFileJpo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodFileJpo(PathMetadata metadata, PathInits inits) {
        this(FoodFileJpo.class, metadata, inits);
    }

    public QFoodFileJpo(Class<? extends FoodFileJpo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.foorun.unieat.domain.file.jpo.QBaseFileJpo(type, metadata, inits);
        this.file = _super.file;
        this.food = inits.isInitialized("food") ? new QFoodJpo(forProperty("food"), inits.get("food")) : null;
        this.sequence = _super.sequence;
        this.thumbnail = _super.thumbnail;
    }

}

