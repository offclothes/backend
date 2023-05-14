package com.app.oc.dto.event;


import com.app.oc.entity.Address;
import com.app.oc.entity.Event;
import com.app.oc.entity.ShoppingMal;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPostResponseDto {

    @JsonProperty("event_seq")
    private Long eventSeq;

    @JsonProperty("shop_name")
    private String shopName;
    private Address address;
    private String content;

    @JsonProperty("write_day")
    private LocalDateTime writeDay;

    @JsonProperty("start_day")
    private LocalDate startDay;

    @JsonProperty("end_day")
    private LocalDate endDay;


    public static MyPostResponseDto myPostResponseDto(ShoppingMal s, Event e) {
        return MyPostResponseDto.builder()
                .eventSeq(e.getEventId())
                .shopName(s.getShopName())
                .address(s.getAddress())
                .content(e.getContent())
                .writeDay(e.getWriteDay())
                .startDay(e.getStartDay())
                .endDay(e.getEndDay())
                .build();
    }
}
