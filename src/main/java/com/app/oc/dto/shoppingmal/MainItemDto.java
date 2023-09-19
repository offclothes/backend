package com.app.oc.dto.shoppingmal;



import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.entity.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 메인 썸네일만 보여주는 곳
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MainItemDto {
    private Long item_seq;
    private String itemTitle;
    private Integer price;
    private String content;
    private UploadFile uploadFile;
    private Integer category;



    public MainItemDto(Item item) {
        this.item_seq =item.getItemId();
        this.itemTitle = item.getItemTitle();
        this.price = item.getPrice();
        this.content = item.getContent();
        this.category = item.getCategory();
    }


}
