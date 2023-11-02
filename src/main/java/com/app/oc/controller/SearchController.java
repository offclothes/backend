package com.app.oc.controller;

import com.app.oc.dto.paging.SearchDto;
import com.app.oc.dto.paging.SearchRequestDto;
import com.app.oc.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    /*
     * 카테고리 검색
     */
    @GetMapping("/category/male")
    public Page<SearchDto> getItemPagingByMale(@RequestParam(defaultValue = "0", required = false) int page) {
        page = page == 0 ? page : page - 1;
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "itemId"));
        return searchService.getItemByCategory(0, pageRequest);
    }

    @GetMapping("/category/female")
    public Page<SearchDto> getItemPagingByFemale(@RequestParam(defaultValue = "0", required = false) int page) {
        page = page == 0 ? page : page - 1;
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "itemId"));
        return searchService.getItemByCategory(1, pageRequest);
    }

    @GetMapping("/category/both")
    public Page<SearchDto> getItemPagingByBoth(@RequestParam(defaultValue = "0", required = false) int page) {
        page = page == 0 ? page : page - 1;
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "itemId"));
        return searchService.getItemByCategory(2, pageRequest);
    }

    /*
     * 키워드 검색
     */
    @GetMapping("/research")
    public Page<SearchDto> getItemByRegion(@RequestBody SearchRequestDto requestDto,
            @RequestParam(defaultValue = "0", required = false) int page) throws IOException {

        String fullAddress = requestDto.getTop().trim() + " " + requestDto.getMid().trim() + " "
                + requestDto.getDong().trim();

        page = page == 0 ? page : page - 1;
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "itemId"));

<<<<<<< HEAD
        Page<SearchDto> itemByRegion = searchService.getItemByRegion(fullAddress, pageRequest);
        return itemByRegion;
=======
        return searchService.getItemByRegion(fullAddress, pageRequest);
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
    }

    @GetMapping("/keyword")
    public Page<SearchDto> getItemPagingByKeyword(@PathVariable String keyword,
            @RequestParam(defaultValue = "0", required = false) int page) throws IOException {
        page = page == 0 ? page : page - 1;
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "itemId"));

        return searchService.getItemByKeyword(keyword, pageRequest);
    }

    /**
     *
     * 전국 8도 데이터 반환하는 api
     * (서울특별시, 경상남도, 경기도, 제주특별자치도, ..)
     * 
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
     * 
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
     * 
     * @return
     */
    @GetMapping("/dong")
    public List<String> getDongRegion(String topRegion, String midRegion) {
        return searchService.findDongRegion(topRegion, midRegion);
    }
}
