package com.app.oc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_seq")
    private Long itemId;

    @Column(name = "itemTitle")
    private String itemTitle;

    private Integer price;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "soldOut")
    private SoldOut soldOut;

    @ManyToOne
    @JoinColumn(name = "shop_seq")
    private Shoppingmall shoppingmall;

    //남 - 0 / 여 - 1 / 공통 -2
    private Integer category;
}
