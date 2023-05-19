package com.app.oc.dto.mypage;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class PwdDto {

    @NotNull
    private String pwd;
    
    @NotNull(message = "비밀번호를 입력해주세요")
    @Size(min = 8,max = 16,message = "비밀번호 길이는 8~16자입니다.")
    private String n_pwd;


}

