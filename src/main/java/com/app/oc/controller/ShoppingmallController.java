package com.app.oc.controller;


import com.app.oc.dto.SellerRequestDto;
import com.app.oc.dto.SellerResponseDto;
import com.app.oc.dto.ShopIntroductionRequestDto;
import com.app.oc.dto.ShopIntroductionResponseDto;
import com.app.oc.exception.MemberErrorResponse;
import com.app.oc.service.ShoppingmallService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.Cookie;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/shop")
@RestController
public class ShoppingmallController {

    private final ShoppingmallService shoppingmallService;

    @PostMapping("/signup")
    public ResponseEntity<SellerResponseDto> sellerJoin(@RequestBody SellerRequestDto requestDto) {

        System.out.println("memberId = " + requestDto.getMemberId());
//        requestDto.setMemberId(requestDto.getMemberId());

        ResponseEntity<SellerResponseDto> responseDto = ResponseEntity.ok(shoppingmallService.sellerSignup(requestDto));
        System.out.println("responseDto = " + responseDto);

        return responseDto;
    }


    @PostMapping("/{shopId}")
    @PutMapping("{shopId}/change")
    public ResponseEntity<ShopIntroductionResponseDto> setIntroduction(@PathVariable Long shopId,
                                                                       @RequestParam MultipartFile shopLogo,
                                                                       @RequestBody ShopIntroductionRequestDto requestDto) {

        return ResponseEntity.ok(shoppingmallService.saveIntroduction(shopId, shopLogo, requestDto));
    }

    @GetMapping("/{shopId}/change")
    public ResponseEntity<ShopIntroductionResponseDto> getIntroduction(@PathVariable Long shopId) {
        return ResponseEntity.ok(shoppingmallService.getIntroduction(shopId));
    }

    @PutMapping("/{shopId}/change") //Post로 요청해도 되나?
    public void changeIntroduction() {

    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<MemberErrorResponse> joinExceptionHandler(IllegalStateException e){

        MemberErrorResponse response = new MemberErrorResponse();
        response.setStatus(HttpStatus.MULTI_STATUS.value());

        response.setMessage(e.getMessage());
        System.out.println("message = " + e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
    }
}
