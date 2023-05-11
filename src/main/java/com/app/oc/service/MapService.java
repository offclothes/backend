package com.app.oc.service;

import com.app.oc.entity.ShoppingMal;
import com.app.oc.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MapService {

    // 아직 더 공부필요
    // 맵을 위한 쇼핑몰 api 전달

    private MapRepository mapRepository;



    public List<ShoppingMal> findAll_Mall(){
        return mapRepository.findAll_Mall();
    }

}
