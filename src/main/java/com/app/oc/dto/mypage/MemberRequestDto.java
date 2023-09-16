package com.app.oc.dto.mypage;

import com.app.oc.entity.Address;
import com.app.oc.entity.Member;
import com.app.oc.entity.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data

public class MemberRequestDto {

    private String memberId;
    private String nickname;
    private String password;

    private String phoneNm;

    private Address address;
    private String gender;
    private Integer length;
    private Integer weight;
    private MemberRole role;

    @Builder
    public MemberRequestDto(Member member) {
        this.memberId = member.getMemberId();
        this.nickname = member.getNickname();
        this.password = member.getPassword();
        this.phoneNm = member.getPhoneNm();
        this.address = member.getAddress();
        this.gender = member.getGender();
        this.length = member.getLength();
        this.weight = member.getWeight();
        this.role = MemberRole.BUYER;
    }

}
