package com.app.oc.controller;

import com.app.oc.dto.mypage.AttenShopDto;
import com.app.oc.dto.mypage.PwdDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false) //롤백안시킴
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void updatPwd() throws BindException {
        ///userController.changePwd("aastupenas26", new PwdDto("122345678", "122345678"));

    }

    @Test
    public void login() {
//        userController.login("Constance", "5ZHAzC0hr3qi");
    }



}