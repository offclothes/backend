package com.app.oc.controller;



import com.app.oc.dto.event.EventRequestDto;
import com.app.oc.dto.event.ResponseeventDto;
import com.app.oc.dto.paging.ItemPageDto;
import com.app.oc.dto.paging.SearchRequestDto;
import com.app.oc.entity.Event;
import com.app.oc.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    /**
    카테고리 검색
     **/
    @GetMapping("/category/male")
    public ItemPageDto getItemPagingByMale(@RequestBody SearchRequestDto requestDto, @PageableDefault(size =6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return searchService.getItemByCategory(0,pageable,requestDto);
    }

    @GetMapping("/category/female")
    public ItemPageDto getItemPagingByFemale(@RequestBody SearchRequestDto requestDto, @PageableDefault(size =6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return searchService.getItemByCategory(1, pageable,requestDto);
    }

    @GetMapping("/category/both")
    public ItemPageDto getItemPagingByBoth(@RequestBody SearchRequestDto requestDto,@PageableDefault(size =6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return searchService.getItemByCategory(2,pageable, requestDto);
    }

    /**
   키워드 검색
    **/
    @GetMapping("/research")
    public ItemPageDto getItemPagingByKeyword(@RequestBody SearchRequestDto requestDto, @PathVariable String keyword,@PageableDefault(size =6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return searchService.getItemByKeyword(keyword, pageable,requestDto);
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