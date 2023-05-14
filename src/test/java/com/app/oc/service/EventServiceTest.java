package com.app.oc.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Autowired
    HttpServletRequest request;



    @Test
    public void listtotal() {

        HttpSession session = request.getSession();
        session.setAttribute("id","Antoni");

        System.out.println("session.getAttribute(\"id\") = " + session.getAttribute("id"));

        eventService.listAll("A",session);

    }






}