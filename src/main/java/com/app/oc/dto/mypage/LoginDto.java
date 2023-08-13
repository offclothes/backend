package com.app.oc.dto.mypage;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {

    @NotNull
    private String id;
    private String pwd;
}
