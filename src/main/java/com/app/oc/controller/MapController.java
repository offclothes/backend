package com.app.oc.controller;



import com.app.oc.dto.map.MapRequestDto;
import com.app.oc.dto.map.MapResponDto;
import com.app.oc.service.MapService;
import com.app.oc.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param requestDto
     * @return
     */
    @GetMapping("/map")
    public List<MapResponDto> shopListByRegion(@RequestBody MapRequestDto requestDto, HttpSession session) {
        //주소 가져오기
        // 우편번호
        // ~~~~~~ 로 35
        //상세주소

        String region = memberService.findOne(session.getId()).getAddress().getAddress1();

        return mapService.findAll_region(region);
    }
}
