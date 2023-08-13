package com.app.oc.dto.mypage;



import com.app.oc.dto.shoppingmal.myshopDto;
import com.app.oc.entity.AttenShop;
import lombok.Data;

@Data
public class AttenShopDto {

    private Long attens_seq;
    private myshopDto shoppingMal;

    public AttenShopDto(AttenShop shop) {
        this.attens_seq = shop.getAttens_seq();
        this.shoppingMal = new myshopDto(shop.getShoppingMal());

    }
}
