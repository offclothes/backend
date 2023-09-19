package com.app.oc.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 관심 상품 엔티티
 */
@Table(name = "attenitem")
@Entity
@Data
public class AttenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aitem_seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;


}
