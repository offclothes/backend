package com.app.oc.dto;

import com.app.oc.entity.Address;
import com.app.oc.entity.Gender;
import com.app.oc.entity.Member;
import com.app.oc.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    @JsonProperty("member_id")
    @NotNull
    private String memberId;
    @NotNull
    private String nickname;
    @NotNull
    private String password;
    @JsonProperty("phone_nm")
    private String phoneNm;
    @NotNull
    private Address address;
    private Gender gender;
    private Integer height;
    private Integer weight;


    public Member toMember() {
        return Member.builder()
                .memberId(memberId)
                .nickname(nickname)
                .password(password)
                .phoneNm(phoneNm)
                .address(address)
                .gender(gender)
                .height(height)
                .weight(weight)
                .role(Role.BUYER)
                .build();
    }

}
