package com.app.oc.entity;

import com.app.oc.dto.mypage.MemberDto;
import com.app.oc.dto.mypage.ResponseMemberDto;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name ="member")
@Getter
@ToString
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성대해 한번 더 체크
public class Member {


    @Id
    @Column(name = "memberID")
    private String memberId;
    private String nickname;
    private String password;
    private String phoneNm;

    //주소
    @Embedded
    private Address address;


    private String gender;
    private Integer length;
    private Integer weight;

    @Enumerated(EnumType.STRING)
    private MemberRole role;


    @Builder
    public void Member(MemberDto member) {
        this.nickname = member.getNickname();
        this.password = member.getPassword();
        this.phoneNm = member.getPhoneNm();
        this.address = member.getAddress();
        this.gender = member.getGender();
        this.length = member.getLength();
        this.weight = member.getWeight();
        this.role = member.getRole();
    }


    /**
     * member수정
     *
     * @param member
     * @param address
     */
    public Member update(ResponseMemberDto member, Address address) {
        this.nickname = member.getNickname();
        this.phoneNm = member.getPhoneNm();
        this.address = address;
        this.gender = member.getGender();
        this.length = member.getLength();
        this.weight = member.getWeight();
        return this;
    }

    /**
     * 비밀번호 수정
     * @param npwd
     */
    public void updatePwd(String npwd) {
        this.password=npwd;
    }

}
