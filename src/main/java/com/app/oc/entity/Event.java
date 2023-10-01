package com.app.oc.entity;


import com.app.oc.dto.event.EventRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 할인/폐점 게시글 엔티티
 */
@Entity
@NoArgsConstructor
@Getter @Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_seq")
    private Long eventId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EventType eventType;

    //연관관계 매핑 필요.
//    @ManyToOne
//    @JoinColumn(name = "memberID")
//    private Member member;
//    ----------------------
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "shop_seq")
    private ShoppingMal shoppingmall;

    private String title;

    private String content;

    @CreatedDate
    @Column(name = "writeDay",updatable = false)
    private LocalDateTime WriteDay;
    @LastModifiedDate
    @Column(name = "modifiedDay")
    private LocalDateTime modifiedDay;

    @Column(name = "startDay")
    private LocalDate startDay;

    @Column(name = "endDay")
    private LocalDate endDay;

    //참조키를 걸어두고 풀 조인을 한다면?
//    @Column(name = "shop_name")
//    private String shopName;
//
//    @Column(name = "shop_address")
//    private String shopAdderss;
//
//    @Column(name = "shop_tel")
//    private String shopTel;

    @Builder
    public Event(Long id, String title, EventType eventType, LocalDate startDay, LocalDate endDay  ,String content) {
        this.eventId = id;
        this.eventType = eventType;
        this.startDay = startDay;
        this.endDay = endDay;
        this.title = title;
        this.content = content;
    }

    public Event updateEvent(EventRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.eventType = requestDto.getEventType();
        this.startDay = requestDto.getStartDate();
        this.endDay = requestDto.getEndDate();

        return this;
    }
}
