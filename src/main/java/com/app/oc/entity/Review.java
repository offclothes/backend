package com.app.oc.entity;/*
package com.app.oc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
//나중에 setter는 다 지우고 따로 작성.
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_seq")
    private int reSeq;

    private String content;

    @Column(name = "write_day")
    private LocalDateTime writeDate;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item_seq")
    private Item item;

    private float rate;

//    public void setWriteDate() {
//        this.writeDate = LocalDateTime.now();
//    }

}
*/
