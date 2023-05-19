package com.app.oc.repository;

import com.app.oc.dto.shoppingmal.MainItemDto;
import com.app.oc.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    Page<MainItemDto> shopMainItems(List<MainItemDto> shopItems, Long id , Pageable pageable);

    List<Item> getcontent(Long id, Pageable pageable);




}
