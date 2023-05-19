package com.app.oc.repository;

import com.app.oc.entity.File;
import com.app.oc.entity.Item;
import com.app.oc.service.ItemService;
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
class FileRepositoryTest {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    ItemService itemService;

    @Test
    public void filePerItem() {
        List<File> byItemItemSeq = fileRepository.findByItem_Item_seq(2L);
        for (File file : byItemItemSeq) {
            System.out.println("file.getStorefile() = " + file.getStorefile());
            System.out.println("file.getStorefile() = " + file.getDatePath());
        }

    }

    @Test
    public void shopFindItem() throws Exception{
    //given

        //when

    //then
    }

}