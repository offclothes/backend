package com.app.oc.controller;

import com.app.oc.dto.event.ResponseeventDto;
import com.app.oc.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
