package com.app.oc.dto.mypage;



import com.app.oc.entity.Address;
import com.app.oc.entity.Member;
import com.app.oc.entity.MemberRole;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberDto {

    private String memberId;
    private String nickname;
    private String password;
    private String phoneNm;
    private Address address;
    private String gender;
    private Integer length;
    private Integer weight;
    private MemberRole role;

    public MemberDto() {
    }

    @Builder
    public MemberDto(Member member) {
        this.memberId = member.getMemberId();
        this.nickname = member.getNickname();
        this.password = member.getPassword();
        this.phoneNm = member.getPhoneNm();
        this.address = member.getAddress();
        this.gender = member.getGender();
        this.length = member.getLength();
        this.weight = member.getWeight();
        this.role = member.getRole();
    }


}
