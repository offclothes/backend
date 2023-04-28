package com.app.oc.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 매장 소개 페이지
 */
@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shoppingmall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_seq")
    private Long shopId;

    private String email;

    @Lob
    @Column(name = "shopLogo")
    private byte[] shopLogo;

    private String style;
    @Lob
    private Byte[] content;

    @Column(name ="shopName")
    private String shopName;

    @Column(name = "shopTel")
    private String shopTel;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.LAZY) //OneToOne은 기본값이 EAGER임
    @JoinColumn(name = "member_mbmberID", referencedColumnName = "memberID")
    private Member member;

    //사업자 등록증 번호 사진
    @Lob
    @Column(name = "shopNumberPic")
    private Byte[] shopNumberImage;

    //임대차 계약서
    @Lob
    @Column(name = "leasePic")
    private Byte[] leaseImage;

    @Enumerated(EnumType.STRING)
    private Approval approval;

    //내가 쓴 글 조회가 가능하려면?
    @OneToMany(mappedBy = "shoppingmall", fetch = FetchType.LAZY)
    private List<Event> eventList = new ArrayList<>();

    @Builder
    public Shoppingmall(Member member, String email, String shopName, String shopTel, Address address, Approval approval) {
        this.member = member;
        this.email = email;
        this.shopName = shopName;
        this.shopTel = shopTel;
        this.address = address;
        this.approval = approval;
    }

    public void setApproval(Approval approval){
        this.approval = approval;
    }
}
