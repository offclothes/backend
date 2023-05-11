package com.app.oc.service;


import com.app.oc.dto.paging.ItemPageDto;
import com.app.oc.dto.paging.SearchDto;
import com.app.oc.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    // 썸네일 추가 작업 필요
    // 지역(시,구,동 / City,District,Neighborhood 필터링 작업 추가)

    private ItemRepository itemRepository;

    public ItemPageDto getItemPagingByMale(Pageable pageable){
        ItemPageDto itemPageDto = new ItemPageDto();
        Page<SearchDto> itemM = itemRepository.searchPageItemM(pageable);

        int startPage = Math.max(1, itemM.getPageable().getPageNumber() - 1);
        int endPage = Math.min(itemM.getTotalPages(), itemM.getPageable().getPageNumber()+3);

        itemPageDto.setItems(itemM);
        itemPageDto.setStartPage(startPage);
        itemPageDto.setEndPage(endPage);

        return itemPageDto;
    }
    public ItemPageDto getItemPagingByFemale(Pageable pageable){
        ItemPageDto itemPageDto = new ItemPageDto();
        Page<SearchDto> itemM = itemRepository.searchPageItemF(pageable);

        int startPage = Math.max(1, itemM.getPageable().getPageNumber() - 1);
        int endPage = Math.min(itemM.getTotalPages(), itemM.getPageable().getPageNumber()+3);

        itemPageDto.setItems(itemM);
        itemPageDto.setStartPage(startPage);
        itemPageDto.setEndPage(endPage);

        return itemPageDto;
    }
    public ItemPageDto getItemPagingByBoth(Pageable pageable){
        ItemPageDto itemPageDto = new ItemPageDto();
        Page<SearchDto> itemM = itemRepository.searchPageItemB(pageable);

        int startPage = Math.max(1, itemM.getPageable().getPageNumber() - 1);
        int endPage = Math.min(itemM.getTotalPages(), itemM.getPageable().getPageNumber()+3);

        itemPageDto.setItems(itemM);
        itemPageDto.setStartPage(startPage);
        itemPageDto.setEndPage(endPage);

        return itemPageDto;
    }

    public ItemPageDto getItemPagingByKeyword(String keyword, Pageable pageable){
        ItemPageDto itemPageDto = new ItemPageDto();
        Page<SearchDto> itemM = itemRepository.findByKeyword(keyword,pageable);

        int startPage = Math.max(1, itemM.getPageable().getPageNumber() - 1);
        int endPage = Math.min(itemM.getTotalPages(), itemM.getPageable().getPageNumber()+3);

        itemPageDto.setItems(itemM);
        itemPageDto.setStartPage(startPage);
        itemPageDto.setEndPage(endPage);

        return itemPageDto;
    }
}
