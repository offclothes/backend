package com.app.oc.dto.event;

import com.app.oc.entity.Address;
import com.app.oc.entity.Event;
import com.app.oc.entity.EventType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ResponseLists {
    private Long eventId;

    private EventType eventType;

    private String title;

    private String shopName;

    private String content;

    private Address address;

    private LocalDateTime writeDay;

    private LocalDate startDay;

    private LocalDate endDay;

    public ResponseLists(Event event) {
        this.eventId = event.getEventId();
        this.eventType = event.getEventType();
        this.title = event.getTitle();
        this.shopName = event.getShoppingmall().getShopName();
        this.content = event.getContent();
        this.address = event.getShoppingmall().getAddress();
        this.writeDay = event.getWriteDay();
        this.startDay = event.getStartDay();
        this.endDay = event.getEndDay();
    }

}