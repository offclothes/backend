package com.app.oc.controller;



import com.app.oc.dto.event.EventRequestDto;
import com.app.oc.dto.event.MyPostResponseDto;
import com.app.oc.dto.event.ResponseeventDto;
import com.app.oc.entity.Event;
import com.app.oc.exception.ErrorResult;
import com.app.oc.service.EventService;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;


    /**
     * 게시글 등록
     */

    @PostMapping("/event/post")
    public ResponseEntity<Event> savePost(@RequestBody EventRequestDto requestDto, @CookieValue String id) throws IOException {
        System.out.println("cookie id = " + id);
        Event saved = eventService.savePost(requestDto, id);
        return (saved != null) ?
                ResponseEntity.status(HttpStatus.OK).body(saved) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    /**
     * 원하는 게시글 수정
     * @param
     */

    @PatchMapping("/event/update/{id}")
    public ResponseEntity<Event> updatePost(@RequestParam Long id, @RequestBody EventRequestDto requestDto) throws IOException {
        Event updated = eventService.updatePost(id, requestDto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



    /**
     * 폐점 전체 조회
     * 전체, 폐점 ,할인 A D C
     * buyer 구분
     */
    @GetMapping("/eventAll")
    public ResponseeventDto responseeventDto(@RequestParam(defaultValue = "A",required = false, name = "state") String state , HttpSession session) {

        //buyer로 구분
        return eventService.listAll(state, session);
    }

    /**
     * 내가 작성한 글 조회
     *
     * @param id
     * @return
     * postman에서 테스트 할 때는 nullPoint 에러 떴지만,
     * 임의의 링크에서 세션을 생성하고 테스트 하면 값이 잘 넘어옴
     */
    @GetMapping("/myPost")
    public ResponseEntity<List<MyPostResponseDto>> getMyEventList(@CookieValue String id) {


        System.out.println("cookie id = " + id);
        return ResponseEntity.ok(eventService.getMyEventPost(id));
    }

    /**
     * 세션 생성하는 사이트(테스트용)
     */
    @GetMapping("/api/test")
    public ResponseEntity<String> createSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        session.setAttribute("id", "test123");

        response.setHeader("id", session.getAttribute("id").toString());
        Cookie cookie = new Cookie("id", session.getAttribute("id").toString());
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(10*60);
        response.addCookie(cookie);

        return ResponseEntity.ok(session.getAttribute("id").toString());
    }

    /**
     * 원하는 게시글 삭제
     * @param eventSeq 글 번호
     */
    @DeleteMapping("/myPost/{eventId}")
    public void deletePost(@PathVariable Long eventSeq) {
        eventService.deletePost(eventSeq);
    }

    /**
     * 매장 정보가 없을 때 발생하는 오류 처리
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoResultException.class)
    public ResponseEntity<ErrorResult> joinExceptionHandler(NoResultException e){

        ErrorResult response = new ErrorResult();
        response.setCode(HttpStatus.NOT_FOUND.value());

        response.setMessage("소유하고 있는 매장이 없습니다");
        System.out.println("message = " + e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
