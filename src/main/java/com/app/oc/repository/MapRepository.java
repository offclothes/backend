package com.app.oc.repository;

import com.app.oc.entity.QShoppingMal;
import com.app.oc.entity.ShoppingMal;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MapRepository extends JpaRepository<ShoppingMal, Long> {
}
