package com.app.oc.service;

import com.app.oc.dto.mypage.MemberRequestDto;
import com.app.oc.dto.mypage.MemberResponseDto;
import com.app.oc.dto.mypage.PwdDto;
import com.app.oc.dto.mypage.ResponseMemberDto;
import com.app.oc.entity.Address;
import com.app.oc.entity.AttenItem;
import com.app.oc.entity.AttenShop;
import com.app.oc.entity.Member;
import com.app.oc.repository.AttenShopRepository;
import com.app.oc.repository.MemberRepository;
import com.app.oc.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//기능을 정의할 수 있고 ,트잭션 관리
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;
    private final AttenShopRepository attenShopRepository;


    /**
     * 회원가입
     * @param memberRequestDTO
     * @return
     */
    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDTO) {

        Member newMember = new Member(memberRequestDTO);
        validateDuplicateMember(newMember); //중복회원 검증

        return MemberResponseDto.of(memberRepository.saveAndFlush(newMember));
    }

    /**
     * 중복 회원 검증
     * @param member
     */
    public void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findById(member.getMemberId());
        if(!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //Member 1명 찾기
    @Transactional(readOnly=true)
    public Member findOne(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("ID가 없습니다."));
    }


    //member 수정
    public String updateMember(String id, ResponseMemberDto buyer) {

        Member buyerEntity = memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("ID가 없습니다."));

        Address address = new Address(buyer.getPostcode(),buyer.getAddress1(),buyer.getAddress2());
        Member update = buyerEntity.update(buyer,address);
        return buyerEntity.getMemberId();
    }


    //비밀번호 update
    public String updatePwd(String id, PwdDto pwdDto ) {
    Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("ID가 없습니다"));
    if (!member.getPassword().equals(pwdDto.getPwd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    member.updatePwd(pwdDto.getN_pwd());
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
