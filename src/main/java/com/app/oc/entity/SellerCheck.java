package com.app.oc.entity;/*
package com.app.oc.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class SellerCheck {

    //사업자등록증 번호
    @Column(name = "shop_number")
    private String shopNumber;

    //사업자 등록증 번호 사진
    @Lob
    @Column(name = "shop_number_image")
    private Byte[] shopNumberImage;

    //임대차 계약서
    @Lob
    @Column(name = "lease_image")
    private Byte[] leaseImage;

    @Enumerated(EnumType.STRING)
    private Approval approval;
}
*/
