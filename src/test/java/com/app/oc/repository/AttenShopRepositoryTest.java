package com.app.oc.repository;

import com.app.oc.entity.AttenShop;
import com.app.oc.entity.Member;
import com.app.oc.entity.ShoppingMal;
import com.app.oc.service.ShopService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class AttenShopRepositoryTest {

    @Autowired
    ShopService shopService;

    @Autowired
    AttenShopRepository attenShopRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findShopAtten() {
//        ShoppingMal myshopById1 = shopService.findMyshopById(1);
//        ShoppingMal myshopById2 = shopService.findMyshopById(2);
//        ShoppingMal myshopById3 = shopService.findMyshopById(3);
//
//        System.out.println("myshopById3 = " + myshopById3);
//        System.out.println("myshopById3 = " + myshopById1);
//        System.out.println("myshopById3 = " + myshopById2);
//
//
//        Member byMember = shopService.findByMember("Annabella");
//
//        AttenShop shop1 = new AttenShop(1,myshopById1,byMember);
//        System.out.println("shop1 = " + shop1);
//        AttenShop shop2= new AttenShop(2,myshopById2,byMember);
//        System.out.println("shop2 = " + shop2);
//        AttenShop shop3 = new AttenShop(3,myshopById3,byMember);
//        em.persist(shop1);
//        em.persist(shop2);
//        em.persist(shop3);



        List<AttenShop> annabella = attenShopRepository.findAttenShop("Annabella");
        for (AttenShop shop : annabella) {
            System.out.println("shop = " + shop);
        }


    }



}