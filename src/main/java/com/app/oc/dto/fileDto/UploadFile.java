package com.app.oc.dto.fileDto;



import com.app.oc.entity.File;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UploadFile {

    private Long item_seq;
    private String storeFileName; // 서부 내부에서 관리하는 파일명
    private String updateDate; // 서부 내부에서 관리하는 파일명
    private String filename; // 서부 내부에서 관리하는 파일명




    public UploadFile(String storeFileName, String updateDate,String filename) {
        this.storeFileName = storeFileName;
        this.updateDate = updateDate;
        this.filename = filename;
    }

    public UploadFile(File file) {
        this.item_seq = file.getItem().getItemId();
        this.storeFileName = file.getStorefile();
        this.updateDate = file.getDatePath();
        this.filename = file.getFilename();

    }


    public File toEntity(UploadFile itemFileRequestDto) {
        return File.builder()
                .datePath(itemFileRequestDto.getUpdateDate())
                .storefile(itemFileRequestDto.getStoreFileName())
                .filename(itemFileRequestDto.getFilename())
                .build();

    }
}
