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

    private MapRepository mapRepository;

    /*
    * 모든 쇼핑몰 리스트 전달
     */

    public List<ShoppingMal> findAll_Mall(){
        return mapRepository.findAll();
    }

}
