package com.app.oc.service;

import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.dto.paging.SearchDto;
import com.app.oc.entity.File;
import com.app.oc.entity.Item;
import com.app.oc.repository.FileRepository;
import com.app.oc.repository.ItemRepository;
import com.app.oc.repository.RegionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    // 지역(시,구,동 / City,District,Neighborhood 필터링 작업 추가)

    private final FileRepository fileRepository;
    private final ItemRepository itemRepository;

    private final RegionRepository regionRepository;

    // 카테고리
    @Transactional
    public Page<SearchDto> getItemByCategory(Integer category, Pageable pageable) {
        SearchDto itemPageDto = new SearchDto();

        // item List
        List<Item> getItems = itemRepository.searchByCategoryAll(category, pageable);
        List<Long> Ids = getItems.stream().map(item -> item.getItemId()).collect(Collectors.toList());

        List<File> fileIn = fileRepository.findFileIn(Ids);
        List<UploadFile> uploadFiles = fileIn.stream().map(file -> new UploadFile(file)).collect(Collectors.toList());

        Map<Long, UploadFile> fileMap = new HashMap<>();
        for (UploadFile uploadFile : uploadFiles) {
            if (uploadFile.getStoreFileName().startsWith("s_")) { // 썸네일
                fileMap.put(uploadFile.getItem_seq(), uploadFile);
            }
        }

        List<SearchDto> items = getsearchDtos(getItems, fileMap);

        Page<SearchDto> searchDtos = itemRepository.pagingByCa(items, category, pageable);

        return searchDtos;
    }

    // 키워드
    @Transactional
    public Page<SearchDto> getItemByKeyword(String keyword, Pageable pageable) {
        // item List
        List<Item> getItems = itemRepository.searchByKeywordAll(keyword, pageable);
        List<Long> Ids = getItems.stream().map(item -> item.getItemId()).collect(Collectors.toList());

        List<File> fileIn = fileRepository.findFileIn(Ids);
        List<UploadFile> uploadFiles = fileIn.stream().map(file -> new UploadFile(file)).collect(Collectors.toList());

        Map<Long, UploadFile> fileMap = new HashMap<>();
        for (UploadFile uploadFile : uploadFiles) {
            if (uploadFile.getStoreFileName().startsWith("s_")) { // 썸네일
                fileMap.put(uploadFile.getItem_seq(), uploadFile);
            }
        }

        List<SearchDto> items = getsearchDtos(getItems, fileMap);

        Page<SearchDto> searchDtos = itemRepository.pagingByKe(items, keyword, pageable);

        return searchDtos;
    }

    @Transactional
    public Page<SearchDto> getItemByRegion(String fullAddress, Pageable pageable) throws IOException {
        // item List
        List<Item> getItems = itemRepository.searchByRegionAll(fullAddress, pageable);

        List<Long> Ids = getItems.stream().map(item -> item.getItemId()).collect(Collectors.toList()); // item id

        List<File> fileIn = fileRepository.findFileIn(Ids);

        List<UploadFile> uploadFiles = fileIn.stream().map(file -> new UploadFile(file)).collect(Collectors.toList());

        Map<Long, UploadFile> fileMap = new HashMap<>();
        for (UploadFile uploadFile : uploadFiles) {
            if (uploadFile.getStoreFileName().startsWith("s_")) { // 썸네일
                fileMap.put(uploadFile.getItem_seq(), uploadFile);
            }
        }

        List<SearchDto> items = getsearchDtos(getItems, fileMap);
        System.out.println("items = " + items.toString());

        Page<SearchDto> searchDtos = itemRepository.pagingByRe(items, fullAddress, pageable);

        return searchDtos;
    }

    private static List<SearchDto> getsearchDtos(List<Item> getItems, Map<Long, UploadFile> fileMap) {
        return getItems.stream().map(item -> {
            SearchDto searchDto = new SearchDto();
            searchDto.setItemTitle(item.getItemTitle());
            searchDto.setUploadFile(fileMap.get(item.getItemId()));
            return searchDto;
        }).collect(Collectors.toList());
    }

    public List<String> findTopRegion() {
        return regionRepository.findTop();
    }

    public List<String> findMiddleRegion(String topRegion) {
        return regionRepository.findMid(topRegion);
    }

    public List<String> findDongRegion(String topRegion, String middleRegion) {
        return regionRepository.findDong(topRegion, middleRegion);
    }

}
