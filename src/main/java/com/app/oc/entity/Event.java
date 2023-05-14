package com.app.oc.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
//연관관계 매핑을
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
    @ManyToOne
    @JoinColumn(name = "shop_seq")
    private ShoppingMal shoppingmall;

    private String title;

    private String content;

    @Column(name = "writeDay")
    private LocalDateTime writeDay;

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



}
