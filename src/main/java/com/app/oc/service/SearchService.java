package com.app.oc.service;


import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.dto.paging.ItemPageDto;
import com.app.oc.dto.paging.SearchDto;
import com.app.oc.entity.File;
import com.app.oc.entity.Item;
import com.app.oc.repository.FileRepository;
import com.app.oc.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    // 지역(시,구,동 / City,District,Neighborhood 필터링 작업 추가)

    private FileRepository fileRepository;
    private ItemRepository itemRepository;



    //카테고리
    @Transactional
    public ItemPageDto getItemByCategory(Integer category, Pageable pageable){
        ItemPageDto itemPageDto = new ItemPageDto();
        //item List
        List<Item> getItems = itemRepository.searchByCategory(category, pageable);
        List<Long> Ids = getItems.stream().map(item -> item.getItemId()).collect(Collectors.toList());

        List<File> fileIn = fileRepository.findFileIn(Ids);
        List<UploadFile> uploadFiles = fileIn.stream().map(file -> new UploadFile(file)).collect(Collectors.toList());

        Map<Long, UploadFile> fileMap = new HashMap<>();
        for (UploadFile uploadFile : uploadFiles) {
            if (uploadFile.getStoreFileName().startsWith("s_")) { //썸네일
                fileMap.put(uploadFile.getItem_seq(), uploadFile);
            }
        }

        List<SearchDto> items = getsearchDtos(getItems, fileMap);

        Page<SearchDto> searchDtos = itemRepository.pagingByCa(items, category, pageable);

        log.info("items :{} ", items);

        int startPage = Math.max(1, searchDtos.getPageable().getPageNumber() - 1);
        int endPage = Math.min(searchDtos.getTotalPages(), searchDtos.getPageable().getPageNumber()+3);


        //페이지 정보
        itemPageDto.setItems(searchDtos);
        itemPageDto.setStartPage(startPage);
        itemPageDto.setEndPage(endPage);

        return itemPageDto;
    }


    //키워드
    @Transactional
    public ItemPageDto getItemByKeyword(String keyword, Pageable pageable){
        ItemPageDto itemPageDto = new ItemPageDto();

        //item List
        List<Item> getItems = itemRepository.searchByKeyword(keyword, pageable);
        List<Long> Ids = getItems.stream().map(item -> item.getItemId()).collect(Collectors.toList());

        List<File> fileIn = fileRepository.findFileIn(Ids);
        List<UploadFile> uploadFiles = fileIn.stream().map(file -> new UploadFile(file)).collect(Collectors.toList());

        Map<Long, UploadFile> fileMap = new HashMap<>();
        for (UploadFile uploadFile : uploadFiles) {
            if (uploadFile.getStoreFileName().startsWith("s_")) { //썸네일
                fileMap.put(uploadFile.getItem_seq(), uploadFile);
            }
        }

        List<SearchDto> items = getsearchDtos(getItems, fileMap);

        Page<SearchDto> searchDtos = itemRepository.pagingByKe(items, keyword, pageable);

        log.info("items :{} ", items);

        int startPage = Math.max(1, searchDtos.getPageable().getPageNumber() - 1);
        int endPage = Math.min(searchDtos.getTotalPages(), searchDtos.getPageable().getPageNumber()+3);


        //페이지 정보
        itemPageDto.setItems(searchDtos);
        itemPageDto.setStartPage(startPage);
        itemPageDto.setEndPage(endPage);

        return itemPageDto;
    }

    private static List<SearchDto> getsearchDtos(List<Item> getItems, Map<Long, UploadFile> fileMap) {
        return getItems.stream().map(item -> {
            SearchDto searchDto = new SearchDto();
            searchDto.setUploadFile(fileMap.get(item.getItemId()));
            return searchDto;
        }).collect(Collectors.toList());
    }


}
