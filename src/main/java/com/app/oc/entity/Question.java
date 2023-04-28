package com.app.oc.entity;/*
package com.app.oc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_number")
    private int questionNumber; //1, 2, 3

    //매핑 필요
    @ManyToOne
    @JoinColumn(name = "item_seq")
    private Item item;

    //원글 번호
    @Column(name = "origin_num")
    private int originNumber; //1, 2, 1

    //원글과 답글의 순서
    private int depth;  //1, 1, 2

    private String content;

    //닉네임으로 보여주는 것이 낫지 않을까?
    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @Column(nullable = false)
    private String title;

    //얘는 작성 시간까지 저장
    private LocalDateTime writeTime;

//    private

}
*/
