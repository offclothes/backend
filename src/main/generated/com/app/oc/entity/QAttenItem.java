package com.app.oc.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttenItem is a Querydsl query type for AttenItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttenItem extends EntityPathBase<AttenItem> {

    private static final long serialVersionUID = 1017851122L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttenItem attenItem = new QAttenItem("attenItem");

    public final NumberPath<Long> aitem_seq = createNumber("aitem_seq", Long.class);

    public final QItem item;

    public final QMember member;

    public QAttenItem(String variable) {
        this(AttenItem.class, forVariable(variable), INITS);
    }

    public QAttenItem(Path<? extends AttenItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttenItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttenItem(PathMetadata metadata, PathInits inits) {
        this(AttenItem.class, metadata, inits);
    }

    public QAttenItem(Class<? extends AttenItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

