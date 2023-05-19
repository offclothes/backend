package com.app.oc.controller;

import com.app.oc.dto.mypage.AttenShopDto;
import com.app.oc.dto.mypage.MemberDto;
import com.app.oc.dto.ResultDto;
import com.app.oc.dto.mypage.PwdDto;
import com.app.oc.dto.mypage.ResponseMemberDto;
import com.app.oc.entity.AttenShop;
import com.app.oc.entity.Member;
import com.app.oc.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
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
    public ResultDto login(String id, String pwd) {
        Member findMember = memberService.findOne(id);
        if (!findMember.getPassword().equals(pwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", findMember.getMemberId());
        log.info("sessionID : {}", session.getAttribute("id"));

        return new ResultDto("로그인 완료");
    }


    /**
     *
     * 로그아웃
     */

    @PostMapping("/logout")
    public ResultDto logout() {
        System.out.println("로그아웃으로 간다.");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("id");
        }
        return new ResultDto("로그아웃되었습니다.");
    }



    /**
     *회원 조회
     * @param id : Member ID
     * @return
     *
     * 0
     */
    @GetMapping("/{id}")
    public MemberDto findById(@PathVariable String id) {
        log.info("phoneNm ={}", memberService.findOne(id).getPhoneNm());
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
    @PutMapping("/{id}")
    public ResultDto updateMember(@PathVariable String id, @RequestBody ResponseMemberDto buyer) {
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


    /**
     * 관심 상품
     * * id - Member id
     */
//    @GetMapping("/attenITem/{id}")
//    public List<MainItemDto> attenItem(@PathVariable String id) {
//        List<AttenItem> byAttenItem = memberService.findByAttenItem(id);
//        List<MainItemDto> result = byAttenItem.stream().map(a -> new MainItemDto(a.getItem())).collect(Collectors.toList());
//        return result;
//
//
//    }



    //    회원탈퇴
//    삭제해야함
//    댓글 있을 시 추후 삭제 (회원 관련 내용 삭제)
//    비밀번호 모를 시, 로그인 페이지 이동
    //0k 22
    @DeleteMapping("/{id}")
    public ResultDto deleteById(@PathVariable String id){
        //회원 삭제
        memberService.delete(id);
        return new ResultDto("탈퇴되었습니다.");
    }


    /**
     * 추후 구현
     * 
     * 
     * 비밀번호 변경
     * x
     */
    @PutMapping("changePwd/{id}")
    public  ResultDto   changePwd(@PathVariable String id,@Valid @RequestBody PwdDto pwdDto) throws BindException {
        //새비밀번호 불일치
        if (!pwdDto.getN_pwd().equals(pwdDto.getPwd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //비밀번호 변경
        memberService.updatePwd(id, pwdDto.getN_pwd());
        return new ResultDto("비밀번호 변경하였습니다.");

    }
    

    //추후 논의  - 비밀번호 확인 방법


    /**
     * - 비밀번호 변경, 회원 탈퇴
     * 비밀번호 확인
     *
     * x
     */
    @PostMapping("confirmpwd/{id}")
    public ResponseEntity comfirmpwd(@PathVariable String id, String pwd,String statue) {
        Member buyer = memberService.findOne(id);
        log.info("buyer.getPassword() ={}", buyer.getPassword());
        log.info("pwdDto.getPwd() ={}", pwd);

        if (!buyer.getPassword().equals(pwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //비밀번호 인증 완료
        switch (statue) {
            case "changePwd":
                //확인되었다는 메시지
                return ResponseEntity.ok(new ResultDto("확인되었습니다."));

            case "deleteMember":
                log.info("여기로 들어옴-delete");
                log.info("id :{}",id);

                memberService.delete(id);

                URI uri = WebMvcLinkBuilder.linkTo(this.getClass()).slash(id).toUri();
                log.info("uri : {}",uri);
                return ResponseEntity.created(uri).body(new ResultDto("회원 탈퇴완료"));
        }

        return ResponseEntity.notFound().build();
    }

}
