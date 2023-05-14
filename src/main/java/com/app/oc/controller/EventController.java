package com.app.oc.controller;

import com.app.oc.dto.event.MyPostResponseDto;
import com.app.oc.dto.event.ResponseeventDto;
import com.app.oc.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * 폐점 전체 조회
     * 전체, 폐점 ,할인 A D C
     * buyer 구분
     */
    @GetMapping("/eventAll")
    public ResponseeventDto responseeventDto(@RequestParam(defaultValue = "A",required = false) String state , HttpSession session) {

        //buyer로 구분
        return eventService.listAll(state, session);
    }

    /**
     * 내가 작성한 글 조회
     *
     * @param session
     * @return
     * postman에서 테스트 할 때는 nullPoint 에러 떴지만,
     * 임의의 링크에서 세션을 생성하고 테스트 하면 값이 잘 넘어옴
     */
    @GetMapping("/myPost")
    public ResponseEntity<List<MyPostResponseDto>> getMyEventList(HttpSession session) {
        String sessionId = (String) session.getAttribute("id");

        System.out.println("sessionId = " + sessionId);
        return ResponseEntity.ok(eventService.getMyEventPost(sessionId));
    }

    /**
     * 세션 생성하는 사이트(테스트용)
     */
//    @GetMapping("/test")
//    public void createSession(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//
//        session.setAttribute("id", "test123");
//    }

    /**
     * 원하는 게시글 삭제
     * @param eventSeq 글 번호
     */
    @DeleteMapping("/myPost/{eventSeq}")
    public void deletePost(@PathVariable Long eventSeq) {
        eventService.deletePost(eventSeq);
    }
}
