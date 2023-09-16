package com.app.oc.service;

import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.entity.File;
import com.app.oc.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    @Value("${file.dir}")
    private String fileDir;

    /**
     * 파일 삭제
     */
    public void fileOneDelete(String storeFileName) throws UnsupportedEncodingException {

        // 파일명만
        java.io.File file = new java.io.File(fileDir + URLDecoder.decode(storeFileName, "UTF-8"));

        // 파일 삭제
        if (file.isFile()) {
            file.delete();
        }

        // 디비 파일 삭제
        File fileDB = fileRepository.findById(storeFileName)
                .orElseThrow(() -> new IllegalStateException("삭제할 파일이 없습니다."));
        fileRepository.delete(fileDB);
    }

    /**
     * 파일 찾기
     * item_Seq
     */

    public List<File> fileFindPerItem(Long itemId) {
        List<File> findFiles = fileRepository.findByItem_Item_seq(itemId);
        return findFiles;
    }

}
