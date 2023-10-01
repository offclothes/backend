package com.app.oc.repository;



import com.app.oc.entity.Event;
import com.app.oc.entity.EventType;
import com.app.oc.entity.ShoppingMal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    /**
     * 게시글 유형에 따른 각 게시글 리스트 조회(할인 or 폐점)
     * @param type
     * @return
     */
    List<Event> findEventByEventType(EventType type);

    /**
     * 이벤트 삭제
     * @param eventId
     */
    void deleteEventByEventId(Long eventId);
    Event findEventByEventId(Long event);
    /**
     * 내가 작성한 게시글 리스트 조회
     * @param shoppingMal
     * @return
     */
    List<Event> findAllByShoppingmall(ShoppingMal shoppingMal);
}
