package com.app.oc.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
class ShopRepositroyTest {

    @Autowired
    ShopRepository shopRepositroy;

    @Test
    public void findByShop_seq() {


    }


}