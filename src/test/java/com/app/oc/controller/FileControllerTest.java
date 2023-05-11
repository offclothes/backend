package com.app.oc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@Transactional
@Commit
class FileControllerTest {

    @Autowired
    FileController fileController;


    @Test
    public void deleteFile() throws UnsupportedEncodingException {

    }

}