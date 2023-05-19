package com.app.oc.controller;


import com.app.oc.dto.map.MapRequestDto;
import com.app.oc.dto.map.MapResponDto;
import com.app.oc.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    /**
     * 메인 홈페이지 지도
     * @param requestDto
     * @return
     */
    @GetMapping("/")
    public List<MapResponDto> shopListByRegion(@RequestBody MapRequestDto requestDto) {
        //주소 가져오기
        // 우편번호
        // ~~~~~~ 로 35
        //상세주소
        String region = requestDto.getRegion();

        return mapService.findAll_region(region);
    }
}
