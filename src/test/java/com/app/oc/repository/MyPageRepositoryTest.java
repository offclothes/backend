package com.app.oc.repository;

import com.app.oc.entity.Member;
import com.app.oc.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false) //롤백안시킴

class MyPageRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    //끝날떄 롤백시켜 넣음
    @Test
    public void testMember() {
        Member member = memberService.findOne("Nicki");
        memberRepository.deleteByNickname(member.getNickname());


    }





}