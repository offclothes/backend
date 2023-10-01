package com.app.oc.controller;

import com.app.oc.dto.paging.ItemPageDto;
import com.app.oc.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

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

    /**
     *
     * 전국 8도 데이터 반환하는 api
     * (서울특별시, 경상남도, 경기도, 제주특별자치도, ..)
     * @return
     */
    @GetMapping("/top")
    public List<String> getTopRegion() {

        return searchService.findTopRegion();
    }

    /**
     *
     * "~~시" or "~~구" or "~~시 ~~구" 데이터 반환하는 api
     * (의정부시, 중구, 남구, 안양시 만안구, ... )
     * @return
     */
    @GetMapping("/mid")
    public ResponseEntity<List<String>> getMiddleRegion(String topRegion) {
        return ResponseEntity.status(HttpStatus.OK).body(searchService.findMiddleRegion(topRegion));
    }

    /**
     *
     * "~~동" 데이터 반환하는 api
     * (방배동, 재궁동, ...)
     * @return
     */
    @GetMapping("/dong")
    public List<String> getDongRegion(String topRegion, String midRegion) {
        return searchService.findDongRegion(topRegion, midRegion);
    }
}