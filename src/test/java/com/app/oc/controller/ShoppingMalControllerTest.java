package com.app.oc.controller;

import com.app.oc.dto.shoppingmal.ItemFileRequestDto;
import com.app.oc.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class ShoppingMalControllerTest {

    @Autowired
     ShopService shopService;
    @Autowired
     ShoppingMalController shoppingMalController;

    @Test
    public void deleteITem() throws Exception{
        shoppingMalController.ItemDelete(4L);
    }


    public void findOneItem() {
        shopService.findByItem(2L);

    }


    @Test
    public void findMyshop() throws Exception{
    //given
//         shoppingMalController.MyShopping(1L);

    //when

    //then
    }

}