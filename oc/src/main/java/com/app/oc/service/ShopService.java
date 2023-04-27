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
    private final MemberRepository memberRepository;
    private final ItemService itemService;


//
//    // item
//    public List<Item> findPerItem(Long id) {
//        return ItemRepository.findByshopItem(id);
//    }



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
}
