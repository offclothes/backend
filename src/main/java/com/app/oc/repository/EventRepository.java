package com.app.oc.repository;

import com.app.oc.entity.AttenShop;
import com.app.oc.entity.Event;
import com.app.oc.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findEventByEventType(EventType type);

}
