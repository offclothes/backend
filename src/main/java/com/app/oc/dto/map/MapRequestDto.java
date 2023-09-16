package com.app.oc.dto.map;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MapRequestDto {

    private String addr1;
    private String addr2;
    private String region; // --> postCode

}
