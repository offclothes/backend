package com.app.oc.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    @NotNull
    private String memberId;
    @NotNull
    private String password;

    public LoginRequestDto loginMember() {
        return LoginRequestDto.builder()
                .memberId(memberId)
                .password(password)
                .build();
    }

}
