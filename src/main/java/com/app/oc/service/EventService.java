package com.app.oc.service;

import com.app.oc.dto.event.ResponseLists;
import com.app.oc.dto.event.ResponseeventDto;
import com.app.oc.entity.Event;
import com.app.oc.entity.EventType;
import com.app.oc.entity.Member;
import com.app.oc.entity.MemberRole;
import com.app.oc.repository.EventRepository;
import com.app.oc.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EventService {


    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    /**
     * 전체 폐점 할인점 조회
     * @return
     */
    public ResponseeventDto listAll(String state, HttpSession session) {


        //세션을 통해 buyer인지 내가 작성한 글인지 확인

        String sessionId = (String) session.getAttribute("id");
        //member로 buyer인지 확인 문제 - 비로그인 , 로그인 ( buyer, seller)
        boolean isSeller = false;
        if (sessionId != null) {
            Member member = memberRepository.findById(sessionId).get();

            System.out.println("member = " + member.getRole());
            if (member.getRole().equals(MemberRole.SELLER)) {
                isSeller = true;

            }
        }


        List<Event> total = null;
        switch (state) {
            case "A": //전체
                total=  eventRepository.findAll();
                break;
            case "D": //할인
                total= eventRepository.findEventByEventType(EventType.D);
                break;
            case "C": //폐점
                total= eventRepository.findEventByEventType(EventType.C);
                break;
        }


        List<ResponseLists> lists = total.stream().map(s -> new ResponseLists(s)).collect(Collectors.toList());
        System.out.println(lists);


        System.out.println("sessionId = " + sessionId);
        System.out.println("isSeller = " + isSeller);

        ResponseeventDto result = new ResponseeventDto(isSeller,lists);
        return result;

    }


}
