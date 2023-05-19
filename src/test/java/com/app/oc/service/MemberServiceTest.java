package com.app.oc.service;

import com.app.oc.dto.mypage.MemberDto;
import com.app.oc.entity.Member;
import com.app.oc.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    void UpdaateMember() {
//        Member result = memberService.findOne("aastupenas26");
//        result.setLength(1900);
//        MemberDto build = MemberDto.builder().member(result).build();

    }

}