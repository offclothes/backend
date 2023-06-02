package com.app.oc.service;



import com.app.oc.repositoryImpl.EventRepositoryImpl;
import com.app.oc.repositoryImpl.ShopRepositoryImpl;
import com.app.oc.dto.event.EventRequestDto;
import com.app.oc.dto.event.MyPostResponseDto;
import com.app.oc.dto.event.ResponseLists;
import com.app.oc.dto.event.ResponseeventDto;
import com.app.oc.entity.*;
import com.app.oc.repository.EventRepository;
import com.app.oc.repository.MemberRepository;
import com.app.oc.repository.ShopRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EventService {


    private final EventRepository eventRepository;

    private final EventRepositoryImpl eventRepositoryImpl;
    private final MemberRepository memberRepository;

    private final ShopRepository shopRepository;
    private final ShopRepositoryImpl shopRepositoryImpl;


    //게시글 등록
    public Event savePost(EventRequestDto requestDto, String memberId){
        Event post = requestDto.toEntity();
        post.setWriteDay(LocalDateTime.now());

        ShoppingMal findShop = shopRepositoryImpl.findByMember(memberId);
        post.setShoppingmall(findShop);

        if(post.getEventId() != null){
            return null;
        }
        return eventRepository.save(post);
    }


    //게시글 등록
    public Event updatePost(Long id, EventRequestDto requestDto){

        log.info("id : {}, title : {}", id, requestDto.getTitle());
        //조회
        Event target = eventRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        //업데이트
        target.updateEvent(requestDto);
        Event updated = eventRepository.save(target);

        return updated;
    }

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

        List<ResponseLists> lists = total.stream().map(e -> new ResponseLists(e)).collect(Collectors.toList());

        System.out.println(lists);


        System.out.println("sessionId = " + sessionId);
        System.out.println("isSeller = " + isSeller);

        ResponseeventDto result = new ResponseeventDto(isSeller,lists);
        return result;
    }

    public List<MyPostResponseDto> getMyEventPost(String memberId) {

        System.out.println("memberId = " + memberId);
        ShoppingMal findShop = shopRepository.findByMember(memberId);
        List<Event> findList = eventRepository.findAllByShoppingmall(findShop);

        return findList.stream().map(event -> {
           return MyPostResponseDto.myPostResponseDto(findShop, event);
        }).collect(Collectors.toList());
    }

    public void deletePost(Long eventSeq) {
        eventRepositoryImpl.deletePost(eventSeq);
    }
}
