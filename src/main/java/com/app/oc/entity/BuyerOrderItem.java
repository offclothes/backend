package com.app.oc.entity;/*
package com.app.oc.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * 무슨 태이블이지? --> 결제 이후 정보 테이브
 *//*

@Entity
@Getter @Setter
@Table(name = "buyer_orderitem")
public class BuyerOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int orderItemId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_seq")
    private List<Item> items = new ArrayList<Item>();

    @Lob
    private Byte[] image;
    private int price;

    private int itemCount;

    @OneToOne
    @JoinColumn(name = "buyer_order_seq")
    private BuyerOrder buyerOrder;


}
*/
