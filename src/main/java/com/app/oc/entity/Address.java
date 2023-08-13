package com.app.oc.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String postcode;
    private String address1;
    private String address2;

    public Address(String postcode, String address1, String address2) {
        this.postcode = postcode;
        this.address1 = address1;
        this.address2 = address2;
    }


    protected Address() {

    }
}
