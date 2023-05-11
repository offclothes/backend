package com.app.oc.controller;


import com.app.oc.entity.ShoppingMal;
import com.app.oc.service.MapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {
    private MapService mapService;

    @GetMapping("/oc")
    public List<ShoppingMal> findAll_Mall(){
        return mapService.findAll_Mall();
    }
}
