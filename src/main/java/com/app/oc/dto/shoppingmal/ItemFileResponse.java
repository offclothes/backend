package com.app.oc.dto.shoppingmal;

import com.app.oc.dto.fileDto.UploadFile;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemFileResponse {
    private UploadFile thumb;
    private List<UploadFile> imageFiles;
}
