package com.app.oc.entity;/*
package com.app.oc.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class AttenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aitem_seq")
    private int aItemSeq;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;
//    private Byte[] image;
    //연관관계 매핑

    @OneToOne
    @JoinColumn(name = "item_seq")
    private Item item;
}
*/
