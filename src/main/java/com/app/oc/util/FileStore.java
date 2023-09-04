package com.app.oc.util;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.app.oc.dto.fileDto.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileStore {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3;


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

        if (th) { //썸네일일 경우
            servername = "s_"+createdSoreFileName(multipartFile.getOriginalFilename());
        }


        // 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());


        try{
        amazonS3.putObject(bucket, servername, multipartFile.getInputStream(), objectMetadata);
        }catch (IOException e) {
            throw new IllegalStateException("S3 파일 업로드에 실패했습니다.");
        }

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
