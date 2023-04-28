package com.app.oc.service;

import com.app.oc.dto.*;
import com.app.oc.entity.Member;

import com.app.oc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원가입
     * @param memberRequestDTO
     * @return
     */
    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDTO) {

        Member newMember = memberRequestDTO.toMember();
        validateDuplicateMember(newMember); //중복회원 검증

        return MemberResponseDto.of(memberRepository.save(newMember));
    }

    public void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getMemberId());
        if(!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    /**
     * 로그인
     * @param requestDto
     * @return 멤버 정보.
     */
    @Transactional
    public MemberResponseDto login(LoginRequestDto requestDto) {

        Member findMember = memberRepository.findOne(requestDto.getMemberId());
        System.out.println("findMember.getPassword() = " + findMember.getPassword());
        System.out.println("requestDto.getPassword() = " + requestDto.getPassword());
        if(findMember == null || (requestDto.getPassword() != findMember.getPassword())) {
            throw new RuntimeException("잘못된 계정정보입니다.");
        }

        return MemberResponseDto.of(findMember);
    }


}
