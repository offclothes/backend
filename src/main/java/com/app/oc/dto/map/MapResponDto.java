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

    private String region;
    public MapResponDto(ShoppingMal shoppingMal) {
        this.shopId = shoppingMal.getShopId();
        this.addr1 = shoppingMal.getAddress().getAddress1();
        this.addr2 = shoppingMal.getAddress().getAddress2();
    }
}
