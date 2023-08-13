package com.app.oc.dto.shoppingmal;



import com.app.oc.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 입점 신청 api의 응답 데이터
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponseDto {

    private Long shopId;
    private String memberId;
    private Address address;
    private String nickname;
    private MemberRole role;
    private String shopName;
    private String shopTel;
    private Approval approval;

    public static SellerResponseDto of(Member member, ShoppingMal shoppingmal) {
        return SellerResponseDto.builder()
                .shopId(shoppingmal.getShopId())
                .memberId(member.getMemberId())
                .address(shoppingmal.getAddress())
                .nickname(member.getNickname())
                .role(member.getRole())
                .shopName(shoppingmal.getShopName())
                .shopTel(shoppingmal.getShopTel())
                .approval(Approval.Y)
                .build();
    }
}
