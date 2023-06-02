package com.app.oc.dto.event;



import com.app.oc.entity.Event;
import com.app.oc.entity.EventType;
import com.app.oc.entity.ShoppingMal;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@ResponseBody
public class MyPostResponseDto {

    private Long eventId;

    private EventType eventType;

    private String title;

    private ShoppingMal shoppingMal;

    private String content;

    private LocalDateTime writeDay;

    private LocalDate startDay;

    private LocalDate endDay;


    public static MyPostResponseDto myPostResponseDto(ShoppingMal s, Event e) {
        return MyPostResponseDto.builder()
                .eventId(e.getEventId())
                .shoppingMal(s)
                .title(e.getTitle())
                .content(e.getContent())
                .writeDay(e.getWriteDay())
                .startDay(e.getStartDay())
                .endDay(e.getEndDay())
                .build();
    }
}
