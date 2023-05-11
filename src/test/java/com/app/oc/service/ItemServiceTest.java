package com.app.oc.service;

import com.app.oc.dto.shoppingmal.DetailItemDto;
import com.app.oc.dto.shoppingmal.ItemFileRequestDto;
import com.app.oc.dto.shoppingmal.MainItemDto;
import com.app.oc.entity.Item;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit @Slf4j
class ItemServiceTest {

    @Autowired
    ItemService itemService;


    @Test
    public void saveorUpdate() throws Exception{
    //given
        ItemFileRequestDto itemFileRequestDto = new ItemFileRequestDto();
        itemFileRequestDto.setItemTitle("나야");
        itemFileRequestDto.setContent("누구구");

        //when
        try {
            itemService.saveItem(itemFileRequestDto);

        } catch (Exception e) {

        }


    //then
    }


    @Test
    public void findShopItem() throws Exception{


    }

    @Test
    public void findDetailOne() throws Exception{
    //given
        PageRequest pageRequest = PageRequest.of(0, 5,Sort.by(Sort.Direction.DESC, "itmeId"));
        Page<MainItemDto> byShopITem = itemService.findByShopITem(1L, pageRequest);
        log.info("byShopITem : {}",byShopITem);
        log.info("0===========================");

        System.out.println("byShopITem.getContent() = " + byShopITem.getContent());
        System.out.println("-------------------------------");

        System.out.println("byShopITem.getTotalPages() = " + byShopITem.getTotalPages());


    }

    @Test
    public void DeleteOneItem() throws Exception{
         itemService.DeleteOneItem(5L);

    }
}