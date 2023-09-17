package com.app.oc.controller;


import com.app.oc.dto.ResultDto;
import com.app.oc.dto.mypage.*;
import com.app.oc.entity.AttenShop;
import com.app.oc.entity.Member;
import com.app.oc.exception.ErrorResult;
import com.app.oc.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Member")
@Slf4j
public class UserController {

    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest request;

    private final MemberService memberService;

    //Long  : Null을 넣을 수 있다



    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResultDto login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        Member findMember = memberService.findOne(loginDto.getId());
        if (!findMember.getPassword().equals(loginDto.getPwd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //세션 생성
        HttpSession session = request.getSession();
        String id = session.getId();
        session.setAttribute("id", findMember.getMemberId());

        //프론트에서도 값을 받기 위해서 쿠키에 아이디 넣어서 응답 헤더를 통해 전송
        response.setHeader("id", session.getAttribute("id").toString());
        Cookie cookie = new Cookie("id", session.getAttribute("id").toString());
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*60);
        response.addCookie(cookie);

        return new ResultDto("로그인 완료");
    }


    /**
     *
     * 로그아웃
     */

    @PostMapping("/logout")
    public ResultDto logout(HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("id");
            Cookie cookie = new Cookie("id", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return new ResultDto("로그아웃되었습니다.");
    }



    /**
     *회원 조회
     * @param id : Member ID
     * @return
     *
     * OK
     */
    @GetMapping("/myPage")
    public MemberDto findById(@CookieValue String id) {
        return new MemberDto(memberService.findOne(id));
    }

    //회원수정

    /**
     *회원 수정
     * @param id : Member ID
     * @return
     *
     * OK
     */
    @PutMapping("/myPage")
    public ResultDto updateMember(@CookieValue String id, @RequestBody ResponseMemberDto buyer) {
        memberService.updateMember(id,buyer);
        return new ResultDto("회원이 수정되었습니다.");
    }

    /**
     * 관심 쇼핑
     * id - Member id
     */
    @GetMapping("/attenshop/{id}")
    public List<AttenShopDto> attenShop(@PathVariable String id) {
        List<AttenShop> byAttenShop = memberService.findByAttenShop(id);
        List<AttenShopDto> result = byAttenShop.stream().map(o -> new AttenShopDto(o)).collect(Collectors.toList());
        return result;
    }



    @PostMapping("changePwd/{id}")
    public  ResultDto   changePwd(@PathVariable String id,@RequestBody PwdDto pwdDto){
        //비밀번호 변경
        memberService.updatePwd(id,  pwdDto);
        return new ResultDto("비밀번호 변경하였습니다.");


    }




    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResult> loginExceptionHandler(RuntimeException e){

        ErrorResult response = new ErrorResult();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 이미 존재하는 회원일 경우의 오류 메시지
     * @param e
     * @return
     */
    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ErrorResult> joinExceptionHandler(IllegalStateException e){
        ErrorResult response = new ErrorResult();
        response.setCode(HttpStatus.MULTI_STATUS.value());

        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
    }
}
