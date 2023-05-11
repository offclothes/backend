package com.app.oc.controller;



import com.app.oc.dto.paging.ItemPageDto;

import com.app.oc.service.SearchService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class SearchController {
    private SearchService searchService;


    @GetMapping("/male")
    public ItemPageDto getItemPagingByMale(@PageableDefault(size = 6) Pageable pageable) {
        return searchService.getItemPagingByMale(pageable);
    }

    @GetMapping("/female")
    public ItemPageDto getItemPagingByFemale(@PageableDefault(size = 6) Pageable pageable) {
        return searchService.getItemPagingByFemale(pageable);
    }

    @GetMapping("/both")
    public ItemPageDto getItemPagingByBoth(@PageableDefault(size = 6) Pageable pageable) {
        return searchService.getItemPagingByBoth(pageable);
    }

    @GetMapping("/research")
    public ItemPageDto getItemPagingByKeyword(@PathVariable String keyword, Pageable pageable) {
        return searchService.getItemPagingByKeyword(keyword,pageable);
    }
}