package com.app.oc.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShoppingMal is a Querydsl query type for ShoppingMal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShoppingMal extends EntityPathBase<ShoppingMal> {

    private static final long serialVersionUID = -190760635L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShoppingMal shoppingMal = new QShoppingMal("shoppingMal");

    public final QAddress address;

    public final EnumPath<Approval> approval = createEnum("approval", Approval.class);

    public final StringPath content = createString("content");

    public final StringPath email = createString("email");

    public final ListPath<Item, QItem> items = this.<Item, QItem>createList("items", Item.class, QItem.class, PathInits.DIRECT2);

    public final QMember member;

    public final NumberPath<Long> shopId = createNumber("shopId", Long.class);

    public final StringPath shopLogo = createString("shopLogo");

    public final StringPath shopName = createString("shopName");

    public final StringPath shopTel = createString("shopTel");

    public final StringPath style = createString("style");

    public QShoppingMal(String variable) {
        this(ShoppingMal.class, forVariable(variable), INITS);
    }

    public QShoppingMal(Path<? extends ShoppingMal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShoppingMal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShoppingMal(PathMetadata metadata, PathInits inits) {
        this(ShoppingMal.class, metadata, inits);
    }

    public QShoppingMal(Class<? extends ShoppingMal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

