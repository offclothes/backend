package com.app.oc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Region {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionId;

    //특별시, 광역시, 도데이터
    @Column(name = "topRegion")
    private String topRegion;

    //"~~시" or "~~시 ~~구" 데이터 담는 변수
    @Column(name = "middleRegion")
    private String middleRegion;

    //siData, guData 선택시 담을 결과 데이터(~~동 형태)
    @Column(name = "dongRegion")
    private String dongRegion;


}
