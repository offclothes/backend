package com.app.oc.controller;

import com.app.oc.dto.map.MapResponDto;
import com.app.oc.service.MapService;
import com.app.oc.service.MemberService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {

    private final MapService mapService;

    private final MemberService memberService;

    public MapController(MapService mapService, MemberService memberService) {
        this.mapService = mapService;
        this.memberService = memberService;
    }

    /**
     * 메인 홈페이지 지도
     * 
     * @param id
     * @return
     */
    @GetMapping("/map")
    public List<MapResponDto> shopListByRegion(@CookieValue(defaultValue = "") String id) {
        // 주소 가져오기
        // 우편번호
        // ~~ 로 35
        // 상세주소

        String region;
        if (id.equals("")) {
            region = "서울특별시 강남구";
        } else {
            String[] fullAddress1 = memberService.findOne(id).getAddress().getAddress1().split(" ");
            region = fullAddress1[0] + " " + fullAddress1[1];
        }
        System.out.println("region = " + region);
        return mapService.findAll_region(region);
    }
}
