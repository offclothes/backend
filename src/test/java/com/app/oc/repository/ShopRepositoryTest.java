package com.app.oc.repository;

import com.app.oc.entity.ShoppingMal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopRepositoryTest {

    @Autowired
    ShopRepository shopRepository;

    @Test
    void findShoppingMalByMemberTest() {
        String id = "test";
        List<ShoppingMal> shoppingMalByMember = shopRepository.findShoppingMalByMember(id);
        System.out.println(shoppingMalByMember);

    }

}