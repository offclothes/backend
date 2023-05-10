package com.app.oc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매장 상세 페이지에서 작성한 정보를 담는 dto
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopIntroductionRequestDto {

    private Byte[] shopLogo;
    private String style;
    private Byte[] content;
}
