package com.app.oc.dto.paging;

import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.entity.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchDto {
    private Long item_seq;
    private String itemTitle;
    private UploadFile uploadFile;
    private Integer category;

    public SearchDto(Item item) {
        this.item_seq = item.getItemId();
        this.itemTitle = item.getItemTitle();
        this.category = item.getCategory();
    }

}
