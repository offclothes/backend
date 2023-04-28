package com.app.oc.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "memberID", unique = true, length = 20)
    private String memberId;

    @Column(nullable = false, unique = true, length = 20)
    //구매자는 닉네임, 판매자는 상호명으로 설정하는게 좋을 것 같음
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(name = "phoneNm", length = 13)
    private String phoneNm;

    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer height;
    private Integer weight;
    @Enumerated(EnumType.STRING)
    private Role role;

    //지역 api 활용할 계획 경기도 안양시 만안구 xx동

    @Builder
    public Member(String memberId, String nickname, String password, String phoneNm, Address address, Gender gender, Integer height, Integer weight, Role role) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.password = password;
        this.phoneNm = phoneNm;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.role = role;
    }
}
