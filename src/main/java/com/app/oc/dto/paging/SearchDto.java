package com.app.oc.dto.paging;

import com.app.oc.entity.File;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchDto {
    private Long item_seq;
    private String itemTitle;
    private Integer category;

    private File file;


}

