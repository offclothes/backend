package com.app.oc.dto;

import com.app.oc.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponseDto {

    private Long shopId;
    private String memberId;
    private Address address;
    private String nickname;
    private Role role;
    private String shopName;
    private String shopTel;
    private Approval approval;

    public static SellerResponseDto of(Member member, Shoppingmall shoppingmall) {
        return SellerResponseDto.builder()
                .shopId(shoppingmall.getShopId())
                .memberId(member.getMemberId())
                .address(shoppingmall.getAddress())
                .nickname(member.getNickname())
                .role(member.getRole())
                .shopName(shoppingmall.getShopName())
                .shopTel(shoppingmall.getShopTel())
                .approval(Approval.Y)
                .build();
    }
}
