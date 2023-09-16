package com.app.oc.dto.shoppingmal;


import com.app.oc.entity.Address;
import com.app.oc.entity.ShoppingMal;
import lombok.Data;

@Data
public class myshopDto {
    private Long shop_seq;
    private String shopLogo;
    private String style;
    private String content;
    private String shopName;
    private Address address;
    private String shopTel;


    public myshopDto(ShoppingMal shoppingMal) {
        this.shop_seq = shoppingMal.getShopId();
        this.shopLogo = shoppingMal.getShopLogo();
        this.style = shoppingMal.getStyle();
        this.content = shoppingMal.getContent();
        this.shopName = shoppingMal.getShopName();
        this.address = shoppingMal.getAddress();
        this.shopTel = shoppingMal.getShopTel();
    }
}
