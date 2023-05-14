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
<<<<<<< HEAD
//         shoppingMalController.MyShopping(1L);
=======

>>>>>>> 4c8e3d6180a67b6e5ab319d5f6a340110a6c35c4

    //when

    //then
    }

}