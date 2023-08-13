package com.app.oc.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 관심 매장 엔티티
 */
@Entity
@Table(name = "attenshop")
@Getter
public class AttenShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attens_seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    private ShoppingMal shoppingMal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public AttenShop(Long attens_seq, ShoppingMal shoppingMal, Member member) {
        this.attens_seq = attens_seq;
        this.shoppingMal = shoppingMal;
        this.member = member;
    }

    public AttenShop() {

    }
}
