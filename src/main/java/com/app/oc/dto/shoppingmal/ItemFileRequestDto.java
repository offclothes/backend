package com.app.oc.dto.shoppingmal;

import com.app.oc.entity.SellState;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 필요 ID
 * :item id, shoppingMal id
 */

@Data
public class ItemFileRequestDto {

    //Item id
    private Long itemId; //등록 , 수정 구분

    //Item
    private String itemTitle;
    private Integer price;
    private  String content;

    //수정 시 변경 할 수 있음
    private SellState sellState;

    //cateGoryNum
    private Integer category;

    //shop_id
    private Long shopId;

//파일들
    private List<MultipartFile> imageFiles;//상세 이미지들
    private MultipartFile thumb; //썸네일 이미지

}
