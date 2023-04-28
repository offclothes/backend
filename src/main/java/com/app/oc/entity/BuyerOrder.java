package com.app.oc.entity;/*
package com.app.oc.entity;

import javax.persistence.*;
import java.time.LocalDate;

*/
/**
 * 무슨 태이블이지? --> 주문자의 정보
 *//*

@Entity
@Table(name = "buyer_orders")
public class BuyerOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_order_seq")
    private int buyerOrderSeq;

    @OneToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @Column(name = "delivery_cost")
    private int deliveryCost;

    @Column(name = "delivery_tel")
    private String deliveryTel;

    @Column(name = "order_adderss1")
    private String orderAdderss1;
    @Column(name = "order_adderss2")
    private String orderAdderss2;
    @Column(name = "order_adderss3")
    private String orderAdderss3;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_state")
    @Enumerated(EnumType.STRING)
    private OrderState orderState;
}
*/
