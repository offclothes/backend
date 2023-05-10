package com.app.oc.service;

import com.app.oc.dto.shoppingmal.MainItemDto;
import com.app.oc.dto.shoppingmal.MyShoppingmalDto;
import com.app.oc.entity.Item;
import com.app.oc.entity.Member;
import com.app.oc.entity.ShoppingMal;
import com.app.oc.repository.ItemRepository;
import com.app.oc.repository.MemberRepository;
import com.app.oc.repository.ShopRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {

    @Autowired
    HttpSession session;


    private final ItemRepository ItemRepository;
    private final ShopRepository shopRepositroy ;

    private final ShopRepositoryImpl shopRepositoryImpl;
    private final MemberRepository memberRepository;
    private final ItemService itemService;

    private final EmailUtil emailUtil;


//
//    // item
//    public List<Item> findPerItem(Long id) {
//        return ItemRepository.findByshopItem(id);
//    }

    @Transactional
    public SellerResponseDto sellerSignup(SellerRequestDto requestDto) {

        Map<String, Object> result = emailUtil.sendEmail(requestDto.getEmail());

        if(result.get("resultCode").equals(200))  {
            Member findMember = shopRepositoryImpl.findMemberByMemberId(requestDto.getMemberId());
            findMember.setRole(Role.SELLER);

            shopRepositoryImpl.saveMember(findMember);
            System.out.println("findMember.getRole() = " + findMember.getRole());

            Shoppingmal shoppingmal = requestDto.toShoppingmall();
            shoppingmall.setMember(findMember);
            System.out.println("shoppingmall.getMember() = " + shoppingmall.getMember());
            shoppingmal.setApproval(Approval.Y);
            shopRepositoryImpl.save(shoppingmall);


            return SellerResponseDto.of(findMember, shoppingmal);
        } else {
            throw new RuntimeException("입점 신청 접수가 안됐습니다. 한 번 더 입력해주세요");
        }

    //각 shop정보
    public ShoppingMal findMyshopById(Long id) {
         ShoppingMal shoppingMal = shopRepositroy.findById(id)
                .orElseThrow(()->new IllegalArgumentException("쇼핑몰이 없습니다.")); //영속화

        return shoppingMal;
    }

    /**
     * Item 찾기
     * @param id
     * @return
     */
    public Item findByItem(Long id) {
        return ItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item이 없습니다.."));
    }

    /**
     * Member 찾기
     * @param id
     * @return
     */
    public Member findByMember(String id) {
         return memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id를 찾지 못했습니다."));
    }



    /**
     *
     * ***세션 ***
     * 쇼핑몰 상세 페이지
     * @param id
     *
     * 세션으로 비교 myShop으로
     */
    public MyShoppingmalDto findShopDetail(Long id, Pageable pageable) {

        //패치로 : member, shop들 전체
        ShoppingMal findShop = findMyshopById(id);

        //세션으로 Id가지고 옴
        String sessionId = (String) session.getAttribute("id");
        log.info("sessionId : {} ", sessionId);
        log.info("findShop.getMember().getMemberId() :{} ", findShop.getMember().getMemberId());


        Boolean myShop = false;
        if (findShop.getMember().getMemberId().equals(sessionId)) {
            myShop = true;
        }

        MyShoppingmalDto result = new MyShoppingmalDto(findShop,myShop);

        //Item 구하기
        Page<MainItemDto> byShopITem = itemService.findByShopITem(id, pageable);
        result.setMainItemDtoList(byShopITem);

        return result;
    }

        /**
         * 매장 정보 작성
         * @param shopId
         * @param shopLogo 썸네일 파일류
         * @param requestDto 입력 정보(내용)
         * @return
         */
        @Transactional
        public ShopIntroductionResponseDto saveIntroduction(Long shopId, MultipartFile shopLogo,
                ShopIntroductionRequestDto requestDto) {

            Shoppingmal findShop = shopRepositoryImpl.findByShopId(shopId);

            findShop.setShopLogo(encodeImageToBase64(shopLogo));
            findShop.setStyle(requestDto.getStyle());
            findShop.setContent(requestDto.getContent());

            shopRepositoryImpl.save(findShop);

            Shoppingmal newFindShop = shopRepositoryImpl.findByShopId(shopId);

            return ShopIntroductionResponseDto.introductionResponseDto(newFindShop);
        }
}
