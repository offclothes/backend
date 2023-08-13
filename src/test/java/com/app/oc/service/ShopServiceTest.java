package com.app.oc.service;

import com.app.oc.dto.shoppingmal.MyShoppingmalDto;
import com.app.oc.repository.ShopRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ShopServiceTest {

    @Autowired
     ShopService shopService;

    @Autowired
    ShopRepository shopRepositroy ;

    @Autowired
    HttpServletRequest request;


    @BeforeEach
    public void Session() {
        HttpSession session = request.getSession();
        session.setAttribute("id","Constance");

    }


    @Test
    public void findShopDetail() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "itemId"));
        MyShoppingmalDto shopDetail = shopService.findShopDetail(1L,pageRequest);
        System.out.println("shopDetail = " + shopDetail);

    }








}