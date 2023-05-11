package com.app.oc.service;

import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.entity.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
class FileServiceTest {

    @Autowired
    FileService fileService;

    @Test
    public void ItemPerFile() throws UnsupportedEncodingException {


        //기존 파일 삭제
        List<File> oldFiles = fileService.fileFindPerItem(5L);
        if (oldFiles != null) {
            for (File file : oldFiles) { //파일 삭제
                fileService.fileOneDelete(new UploadFile(file.getStorefile(),file.getDatePath(),file.getFilename()));
            }
        }

        for (File file : oldFiles) {

            System.out.println("file.getDatePath() = " + file.getDatePath());
            System.out.println("file.getStorefile() = " + file.getStorefile());
        }

    }



}