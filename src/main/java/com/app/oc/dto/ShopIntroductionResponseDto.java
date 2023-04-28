package com.app.oc.dto;

import com.app.oc.entity.Address;
import com.app.oc.entity.Shoppingmall;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매장 상세 페이지 결과 데이터 담는 dto
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopIntroductionResponseDto {

    private Long shopId;
    private byte[] shopLogo;
    private String style;
    private Byte[] content;
    private String shopName;
    private String shopTel;
    private Address address;


    public static ShopIntroductionResponseDto introductionResponseDto(Shoppingmall shoppingmall) {
        return ShopIntroductionResponseDto.builder()
                .shopId(shoppingmall.getShopId())
                .shopLogo(shoppingmall.getShopLogo())
                .address(shoppingmall.getAddress())
                .shopName(shoppingmall.getShopName())
                .shopTel(shoppingmall.getShopTel())
                .content(shoppingmall.getContent())
                .style(shoppingmall.getStyle())
                .build();
    }
}
