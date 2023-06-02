package com.app.oc.controller;



import com.app.oc.dto.ResultDto;
import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController {

    @Value("${file.dir}")
    private String fileDir;

    private final FileService fileService;


    /**
     * 
     * Item 파일 삭제
     * 파일만 삭제되게 구분(item삭재x)
     * UploadFile : datePath , filename
     *
     * OK
     *
     */

    @DeleteMapping("/deleteFile")
    public ResultDto FileDelete(@RequestBody UploadFile uploadFile) throws UnsupportedEncodingException {
        fileService.fileOneDelete(uploadFile);
        return new ResultDto("파일을 삭제하였습니다.");
    }

    /**
     *파일 이미지 보여주기
     * @param fileName  : 파일명(서버)
     * @return
     *
     * 추후 논의
     *
     * date/filename
     */
    @PostMapping("/display")
    public ResponseEntity<byte[]> display(String fileName) throws IOException {

        log.info("file name : " + fileName);

        File file = new File(fileDir, fileName);

        ResponseEntity<byte[]> result = null;
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", Files.probeContentType(file.toPath()));
        result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        return result;
    }


}
