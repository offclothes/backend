package com.app.oc.dto.shoppingmal;

import com.app.oc.entity.*;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
public class MyShoppingmalDto {

//    private MemberRole role;
    private Boolean myshop;
    private Long shop_seq;
    private String shopLogo;
    private String style;
    private String content;
    private String shopName;
    private String email;
    private Address address;


    private String shopTel;

    private Page<MainItemDto> mainItemDtoList;

    public MyShoppingmalDto(ShoppingMal shoppingMal,Boolean myshop) {
        this.shop_seq = shoppingMal.getShopId();
        this.shopLogo =shoppingMal.getShopLogo();
        this.style = shoppingMal.getStyle();
        this.content = shoppingMal.getContent();
        this.shopName = shoppingMal.getShopName();
        this.address  = shoppingMal.getAddress();
        this.shopTel = shoppingMal.getShopTel();
        this.myshop = myshop;
        this.email = shoppingMal.getEmail();
  //      this.role = shoppingMal.getMember().getRole();
    }

}
