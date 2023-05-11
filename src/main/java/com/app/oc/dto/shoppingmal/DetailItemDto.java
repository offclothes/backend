package com.app.oc.dto.shoppingmal;

import com.app.oc.dto.fileDto.UploadFile;
import com.app.oc.entity.Item;
import com.app.oc.entity.MemberRole;
import com.app.oc.entity.SellState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Item상세 페이지
 */
@Data
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailItemDto {
    private Long item_seq;
    private Boolean myshop;

    //private MemberRole role;
    private SellState sellState;
    private String itemTitle;
    private Integer price;
    private String content;
    private UploadFile thumb;
    private List<UploadFile> imageFiles;

    public DetailItemDto(Item item) {
        this.item_seq = item.getItemId();
        this.itemTitle = item.getItemTitle();
        this.price = item.getPrice();
        this.sellState = item.getSellState();
        this.content = item.getContent();
    }
}
