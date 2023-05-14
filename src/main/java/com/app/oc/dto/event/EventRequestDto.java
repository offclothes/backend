package com.app.oc.dto.event;

import com.app.oc.entity.Event;
import com.app.oc.entity.EventType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class EventRequestDto {

    private Long id;
    private EventType eventType;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalDateTime WriteDate;
    private LocalDateTime modifiedDate;
    public Event toEntity() {
        Event build = Event.builder()
                .id(id)
                .title(title)
                .eventType(eventType)
                .startDay(startDate)
                .endDay(endDate)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public EventRequestDto(Long id, String title, EventType eventType , String content, LocalDate startDate, LocalDate endDate, LocalDateTime writeDate, LocalDateTime modifiedDate ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.eventType = eventType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.WriteDate = writeDate;
        this.modifiedDate = modifiedDate;
    }

}
