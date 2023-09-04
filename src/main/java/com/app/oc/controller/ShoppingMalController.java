package com.app.oc.controller;



import com.app.oc.dto.ResultDto;
import com.app.oc.dto.shoppingmal.*;
import com.app.oc.service.ItemService;
import com.app.oc.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RequestMapping("/shop")
@Slf4j
@RestController
public class ShoppingMalController {

    private final ShopService shopService;
    private final ItemService itemService;


    /**
     * 입점 신청
     * @param requestDto
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<SellerResponseDto> sellerJoin(@RequestBody SellerRequestDto requestDto) {
        ResponseEntity<SellerResponseDto> responseDto = ResponseEntity.ok(shopService.sellerSignup(requestDto));
        return responseDto;
    }


    /**
     * 매장 정보 작성(정상 작동하는 지 확인 필요)
     * @param shopId
     * @param requestDto
     * @return
     */
    @PostMapping("/{shopId}")
    public ResponseEntity<ShopIntroductionResponseDto> setIntroduction(@PathVariable Long shopId,
                                                                       @RequestBody ShopIntroductionRequestDto requestDto) {

        return ResponseEntity.ok(shopService.saveIntroduction(shopId, requestDto));
    }

    /**
     * 변경하기 전 매장 정보 불러오는 api
     * @param shopId
     * @return
     */
    @GetMapping("/{shopId}/change")
    public ResponseEntity<ShopIntroductionResponseDto> getIntroduction(@PathVariable Long shopId) {
        return ResponseEntity.ok(shopService.getIntroduction(shopId));
    }

    /**
     * 변경한 매장 정보 저장하는 api
     * @param shopId
     * @param requestDto
     * @return
     */
    @PatchMapping("/{shopId}/change")
    public ResponseEntity<ShopIntroductionResponseDto> changeIntroduction(@PathVariable Long shopId,
                                                                          @RequestBody ShopIntroductionRequestDto requestDto) {

        return ResponseEntity.ok(shopService.saveIntroduction(shopId,requestDto));
    }

    /**
     * Item 저장(+file) /- 등록 / 수정
     * OK
     */
    @PostMapping("/saveItem")
    public ResultDto SaveItem(ItemFileRequestDto itemFileRequestDto) throws IOException {

        //DB isert
        String name = itemService.saveItem(itemFileRequestDto);

        return new ResultDto("상품이 "+name+"되었습니다.");
    }


    /**
     *
     * @param id :
     *  ShoppingMal 상세 보기
     *           추후 세션으로 ID가져오기
     *
     *           OK
     */
    @GetMapping("/{id}")
    public MyShoppingmalDto MyShopping(@PathVariable Long id,@RequestParam(defaultValue = "0",required = false) int page) {
        page = page == 0? page : page - 1;
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "itemId"));
        return shopService.findShopDetail(id,pageRequest);
    }


    /**
     *
     * ITem /File
     *
     * item 상세페이지 :
     * id - item id/
     *
     * 세션으로 Id가져와야 함
     *
     * OK
     */
    @GetMapping("/item/{id}")
    public DetailItemDto Myitem(@PathVariable Long id) {
        return itemService.findDetailOne(id);
    }

    /**
     * item - 삭제(파일포함)
     * {id} - item의 id
     * 
     * 로그인 세션추가작업
     */
    @DeleteMapping("/item/{id}")
    public ResultDto ItemDelete(@PathVariable Long id) throws UnsupportedEncodingException {
        itemService.DeleteOneItem(id);
        return new ResultDto("삭제되었습니다.");
    }


}
