package com.app.oc.dto;

import com.app.oc.entity.Address;
import com.app.oc.entity.Member;
import com.app.oc.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {

    private String memberId;
    private String nickname;
    private Address address;
    private Role role;

    public static MemberResponseDto of(Member member) {
        System.out.println("member = " + member.getMemberId());
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .role(member.getRole())
                .build();
    }

}
