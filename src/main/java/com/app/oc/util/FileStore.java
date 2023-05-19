package com.app.oc.util;

import com.app.oc.dto.fileDto.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;


    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
            List<UploadFile> storeFileResult = new ArrayList<>();

            for (MultipartFile multipartFile : multipartFiles) {
                if (!multipartFile.isEmpty()) {
                    storeFileResult.add(storeFile(multipartFile, false));
                }
            }
        return storeFileResult;
    }

    //파일 업로드
    public UploadFile storeFile(MultipartFile multipartFile,Boolean th) throws IOException {

        File fileone = new File(fileDir); //파일 저장
        //Path path = Paths.get(fileDir);

        if (!fileone.exists()) {
            boolean mkdirs = fileone.mkdirs();
            log.info("mkdirs = ",mkdirs);
        }



        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename(); //실제 파일명

        String servername = "";
        String updateDate = "";


        //서버저장
        String filename = multipartFile.getOriginalFilename();
         updateDate = getDirectoryForm(); //날짜
         servername = createdSoreFileName(originalFilename);
//
//        File uploadPaths = new File( fileDir,updateDate); //파일 저장
//
//        if (!uploadPaths.exists()) {
//            uploadPaths.mkdirs();
//        }

        if (th) { //썸네일일 경우
            servername = "s_"+createdSoreFileName(multipartFile.getOriginalFilename());
        }

        File file = new File(fileDir,servername); //파일 저장
        log.info("file : {}", file);

        //만약 파일의 네임과 같은게 있다면 저장을
        multipartFile.transferTo(file);


        return new UploadFile(servername,updateDate,filename);

    }

    private String getDirectoryForm() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
        return simpleDateFormat.format(today);
    }



    //서버에 저장할 파일명
    private String createdSoreFileName(String originalFilename) {
        String ext =  extractExt(originalFilename); //확장자
        //서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString();
        String storeFileName =uuid+"."+ext;
        return storeFileName;


    }

        //.png
        private String extractExt(String originalFilename) {
            int pos = originalFilename.lastIndexOf("."); //.위치
            String ext = originalFilename.substring(pos + 1); // 확장자
            return ext;
    }

}
