package com.app.oc.entity;/*
package com.app.oc.entity;

import javax.persistence.*;

@Entity
public class AttenShop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atten_seq")
    private int attenSeq;
    
    //연관관계 매핑 필요?
    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;
    //연관관계 매핑 필요?
//    @Column(name = "shop_name")
//    private String shopName;

    @OneToOne
    @JoinColumn(name = "shop_seq")
    private Shop shop;

}
*/
