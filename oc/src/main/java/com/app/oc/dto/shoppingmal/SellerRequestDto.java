package com.app.oc.dto;

import com.app.oc.entity.Address;
import com.app.oc.entity.Shoppingmall;
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
public class SellerRequestDto {

    @JsonProperty("member_id")
    @NotNull
    private String memberId;
    @JsonProperty("shop_name")
    @NotNull
    private String shopName;
    @NotNull
    private Address address;
    @NotNull
    private String email;
    @JsonProperty("shop_tel")
    @NotNull
    private String shopTel;

    public Shoppingmal toShoppingmal() {
        return Shoppingmal.builder()
                .shopName(shopName)
                .address(address)
                .email(email)
                .shopTel(shopTel)
                .build();
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
