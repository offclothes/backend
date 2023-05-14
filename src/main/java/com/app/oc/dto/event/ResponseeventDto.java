package com.app.oc.dto.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ResponseeventDto {

    //buyer인지
    private boolean isSeller;
    private List<ResponseLists> list;

    public ResponseeventDto(boolean isSeller,List<ResponseLists> list) {
        this.isSeller = isSeller;
        this.list = list;
    }

}
