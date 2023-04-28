package com.app.oc.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class MemberErrorResponse extends RuntimeException{
    private int status;
    private String message;
}
