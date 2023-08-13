package com.app.oc.dto.paging;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Data
public class ItemPageDto {
    Page<SearchDto> items;
    Integer startPage;
    Integer endPage;


}
