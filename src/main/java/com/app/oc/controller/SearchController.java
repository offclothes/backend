package com.app.oc.controller;



import com.app.oc.dto.paging.ItemPageDto;

import com.app.oc.dto.paging.SearchDto;
import com.app.oc.service.SearchService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.event.PaintEvent;

@RestController
public class SearchController {
    private SearchService searchService;

    /*
    카테고리 검색
     */
    @GetMapping("/category/male")
    public ItemPageDto getItemPagingByMale(@PageableDefault(size =6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return searchService.getItemByCategory(0,pageable);
    }

    @GetMapping("/category/female")
    public ItemPageDto getItemPagingByFemale(@PageableDefault(size =6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return searchService.getItemByCategory(1, pageable);
    }

    @GetMapping("/category/both")
    public ItemPageDto getItemPagingByBoth(@PageableDefault(size =6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return searchService.getItemByCategory(2,pageable);
    }

    /*
   키워드 검색
    */
    @GetMapping("/research")
    public ItemPageDto getItemPagingByKeyword(@PathVariable String keyword,@PageableDefault(size =6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return searchService.getItemByKeyword(keyword, pageable);
    }
}