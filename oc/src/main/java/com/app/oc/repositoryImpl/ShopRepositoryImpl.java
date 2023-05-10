package com.app.oc.repository;

import com.app.oc.entity.Item;
import com.app.oc.entity.Member;
import com.app.oc.entity.Shoppingmall;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class ShopRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public void save(Shoppingmal shoppingmal) {
        em.persist(shoppingmal);
    }

    public void saveMember(Member member) { em.persist(member); }

    public Member findMemberByMemberId(String memberId) {
       return em.find(Member.class, memberId);
    }

}
