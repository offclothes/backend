package com.app.oc.dto.mypage;

import com.app.oc.entity.Address;
import com.app.oc.entity.Member;
import com.app.oc.entity.MemberRole;
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
    private MemberRole role;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .role(member.getRole())
                .build();
    }

}
