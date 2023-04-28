package com.app.oc.controller;


import com.app.oc.dto.LoginRequestDto;
import com.app.oc.dto.MemberRequestDto;
import com.app.oc.dto.MemberResponseDto;
import com.app.oc.exception.MemberErrorResponse;
import com.app.oc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/login")
    public MemberResponseDto login(@RequestBody LoginRequestDto requestDTO) {

        return memberService.login(requestDTO);
    }


    @PostMapping("/signup")
    public MemberResponseDto create(@RequestBody MemberRequestDto requestDTO) {
        System.out.println("requestDTO.getMemberId() = " + requestDTO.getMemberId());
        MemberResponseDto newMember = memberService.signup(requestDTO);
        return newMember;
    }

//    @GetMapping("/test")
//    public String testCookie(HttpServletResponse response) {
//        Cookie cookie = new Cookie("username", "test1234");
//        response.addCookie(cookie);
//    }

    /**
     * 로그인 시 아이디나 비밀번호가 일치하지 않을 경우의 오류
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<MemberErrorResponse> loginExceptionHandler(RuntimeException e){

        MemberErrorResponse response = new MemberErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        response.setMessage(e.getMessage());
        System.out.println("message = " + e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 이미 존재하는 회원일 경우의 오류 메시지
     * @param e
     * @return
     */
    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<MemberErrorResponse> joinExceptionHandler(IllegalStateException e){

        MemberErrorResponse response = new MemberErrorResponse();
        response.setStatus(HttpStatus.MULTI_STATUS.value());

        response.setMessage(e.getMessage());
        System.out.println("message = " + e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
    }
    
}
