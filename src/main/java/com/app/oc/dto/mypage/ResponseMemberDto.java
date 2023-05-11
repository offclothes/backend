package com.app.oc.dto.mypage;

import com.app.oc.entity.Address;
import com.app.oc.entity.MemberRole;
import lombok.Data;

/**
 * Member회원 수정
 */

@Data
public class ResponseMemberDto {

    private String memberId;
    private String nickname;
    private String phoneNm;

    private String postcode;
    private String address1;
    private String address2;

    private String gender;
    private Integer length;
    private Integer weight;
}
