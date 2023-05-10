package com.app.oc.service;

import com.app.oc.dto.mypage.MemberDto;
import com.app.oc.dto.mypage.ResponseMemberDto;
import com.app.oc.entity.Address;
import com.app.oc.entity.AttenItem;
import com.app.oc.entity.AttenShop;
import com.app.oc.entity.Member;
import com.app.oc.repository.AttenShopRepository;
import com.app.oc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//기능을 정의할 수 있고 ,트잭션 관리
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository me0mberRepository;
    private final AttenShopRepository attenShopRepository;

    //Member 1명 찾기
    @Transactional(readOnly=true)// jpa 변경감지 내부기능 활성화x,update시 정합성 유지,
    public Member findOne(String id) {
        return memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("ID가 없습니다."));
    }


    //member 수정
    public String updateMember(String id, ResponseMemberDto buyer) {

        Member buyerEntity = memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("ID가 없습니다."));

        Address address = new Address(buyer.getPostcode(),buyer.getAddress1(),buyer.getAddress2());
        Member update = buyerEntity.update(buyer,address);
        log.info("update : {}",update);
        return buyerEntity.getMemberId();
    }

    //Member 삭제
    public String delete(String id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID가 없습니다."));
        memberRepository.delete(member);
        return "ok"; 
    }

    //비밀번호 update
    public String updatePwd(String id, String nPwd) {
    Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("ID가 없습니다"));
    member.updatePwd(nPwd);
    return member.getMemberId();
}


//관심 쇼핑몰
    @Transactional(readOnly=true)
    public List<AttenShop> findByAttenShop(String id) {
        return attenShopRepository.findAttenShop(id);
    }


    //관심 아이템
    @Transactional(readOnly=true)
    public List<AttenItem> findByAttenItem(String id) {
        return attenShopRepository.findAttenItem(id);
    }
}
