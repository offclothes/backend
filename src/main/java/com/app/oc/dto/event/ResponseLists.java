package com.app.oc.dto.event;

import com.app.oc.entity.Event;
import com.app.oc.entity.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ResponseLists {
    private Long eventId;

    private EventType eventType;

    private String title;

    private String content;

    private LocalDateTime writeDay;

    private LocalDate startDay;

    private LocalDate endDay;

    public ResponseLists(Event event) {
        this.eventId = event.getEventId();
        this.eventType = event.getEventType();
        this.title = event.getTitle();
        this.content = event.getContent();
        this.writeDay = event.getWriteDay();
        this.startDay = event.getStartDay();
        this.endDay = event.getEndDay();
    }
}
