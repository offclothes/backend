package com.app.oc.dto.map;



import com.app.oc.entity.ShoppingMal;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MapResponDto {

    private Long shopId;
    private String addr1;
    private String addr2;

    private String shopName;

    private String region;
    public MapResponDto(ShoppingMal shoppingMal) {
        this.shopId = shoppingMal.getShopId();
        this.addr1 = shoppingMal.getAddress().getAddress1();
        this.addr2 = shoppingMal.getAddress().getAddress2();
        this.shopName = shoppingMal.getShopName();
    }

    public static MapResponDto shopList(ShoppingMal s) {
        return MapResponDto.builder()
                .shopId(s.getShopId())
                .shopName(s.getShopName())
                .addr1(s.getAddress().getAddress1())
                .addr2(s.getAddress().getAddress2())
                .build();
    }
}
