package com.app.oc.service;

import com.app.oc.dto.map.MapResponDto;
import com.app.oc.entity.ShoppingMal;
import com.app.oc.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    /*
     * 모든 쇼핑몰 받아오기
     * public List<MapDto> findAll_Mall(String region){
     * 
     * //받아온 지역으로 리스트 받아오기
     * List<ShoppingMal> shopList = mapRepository.findAll();
     * List<MapDto> lists = shopList.stream().map(s -> new
     * MapDto()).collect(Collectors.toList());
     * 
     * return lists;
     * }
     */

    /**
     * 모든 쇼핑몰 리스트 전달
     */
    public List<MapResponDto> findAll_region(String region) {

        // 받아온 지역으로 리스트 받아오기
        List<ShoppingMal> findShop = mapRepository.findByAddress_Address1Containing(region);

        return findShop.stream().map(shop -> {
            return MapResponDto.shopList(shop);
        }).collect(Collectors.toList());
    }

}
