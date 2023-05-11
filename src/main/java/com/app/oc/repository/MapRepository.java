package com.app.oc.repository;

import com.app.oc.entity.QShoppingMal;
import com.app.oc.entity.ShoppingMal;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MapRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public MapRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    public List<ShoppingMal> findAll_Mall(){
        return queryFactory
                .selectFrom(QShoppingMal.shoppingMal)
                .fetch();
    }

}
