package com.app.oc.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    @JsonProperty("post_code")
    private String postCode;

    //주소(도, 시, 동 or )
    private String address1;
    //상세주소
    private String address2;

    public Address(String postCode, String address1, String address2) {
        this.postCode = postCode;
        this.address1 = address1;
        this.address2 = address2;
    }

    protected Address() {
    }
}
