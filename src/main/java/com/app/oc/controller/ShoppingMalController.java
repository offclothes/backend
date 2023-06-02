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

        log.info("memberId = {} ", requestDto.getMemberId());

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

    @GetMapping("/{shopId}/change")
    public ResponseEntity<ShopIntroductionResponseDto> getIntroduction(@PathVariable Long shopId) {
        return ResponseEntity.ok(shopService.getIntroduction(shopId));
    }

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
     * **미완료 - 페이징
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
     * 추후 연관관계 삭제
     * 로그인 세션추가작업
     *
     * OK(미완성)
     */
    @DeleteMapping("/item/{id}")
    public ResultDto ItemDelete(@PathVariable Long id) {
        itemService.DeleteOneItem(id);
        return new ResultDto("삭제되었습니다.");
    }


}
