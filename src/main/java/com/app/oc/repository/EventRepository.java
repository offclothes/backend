package com.app.oc.repository;



import com.app.oc.entity.Event;
import com.app.oc.entity.EventType;
import com.app.oc.entity.ShoppingMal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findEventByEventType(EventType type);


    List<Event> findAllByShoppingmall(ShoppingMal shoppingMal);
}
