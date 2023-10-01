package com.app.oc.repository;

import com.app.oc.dto.paging.SearchDto;
import com.app.oc.dto.shoppingmal.MainItemDto;
import com.app.oc.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    Page<MainItemDto> shopMainItems(List<MainItemDto> shopItems, Long id , Pageable pageable);

    List<Item> getcontent(Long id, Pageable pageable);

    List<Item> searchByCategoryAll(Integer category, Pageable pageable);
    List<Item> searchByKeywordAll(String keyword, Pageable pageable);

    List<Item> searchByCategory(String fullAddress, Integer category, Pageable pageable);
    List<Item> searchByKeyword(String fullAddress,String keyword, Pageable pageable);
    Page<SearchDto> pagingByCa(List<SearchDto> items, Integer category, Pageable pageable);
    Page<SearchDto> pagingByKe(List<SearchDto> items, String keword, Pageable pageable);



}
