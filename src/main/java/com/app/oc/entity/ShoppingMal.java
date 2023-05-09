package com.app.oc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString @Setter
public class ShoppingMal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="shop_seq" )
    private Long shopId;
    private String shopLogo; //파일 처리
    private String style;
    private String content;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
    private String shopName;

    //shopAddr
    @Embedded
    private Address address;
    private String shopTel;

    @OneToMany(mappedBy = "itemId")
    private List<Item> items = new ArrayList<>();

//Check
    private String password;
    private Integer leasePic;
    private String Approval;

}
