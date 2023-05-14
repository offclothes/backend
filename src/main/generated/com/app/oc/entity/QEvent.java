package com.app.oc.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -708049713L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvent event = new QEvent("event");

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> endDay = createDate("endDay", java.time.LocalDate.class);

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final EnumPath<EventType> eventType = createEnum("eventType", EventType.class);

    public final QShoppingMal shoppingmall;

    public final DatePath<java.time.LocalDate> startDay = createDate("startDay", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> writeDay = createDateTime("writeDay", java.time.LocalDateTime.class);

    public QEvent(String variable) {
        this(Event.class, forVariable(variable), INITS);
    }

    public QEvent(Path<? extends Event> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvent(PathMetadata metadata, PathInits inits) {
        this(Event.class, metadata, inits);
    }

    public QEvent(Class<? extends Event> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shoppingmall = inits.isInitialized("shoppingmall") ? new QShoppingMal(forProperty("shoppingmall"), inits.get("shoppingmall")) : null;
    }

}

