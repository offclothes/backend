package com.app.oc.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttenShop is a Querydsl query type for AttenShop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttenShop extends EntityPathBase<AttenShop> {

    private static final long serialVersionUID = 1018137813L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttenShop attenShop = new QAttenShop("attenShop");

    public final NumberPath<Long> attens_seq = createNumber("attens_seq", Long.class);

    public final QMember member;

    public final QShoppingMal shoppingMal;

    public QAttenShop(String variable) {
        this(AttenShop.class, forVariable(variable), INITS);
    }

    public QAttenShop(Path<? extends AttenShop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttenShop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttenShop(PathMetadata metadata, PathInits inits) {
        this(AttenShop.class, metadata, inits);
    }

    public QAttenShop(Class<? extends AttenShop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.shoppingMal = inits.isInitialized("shoppingMal") ? new QShoppingMal(forProperty("shoppingMal"), inits.get("shoppingMal")) : null;
    }

}

