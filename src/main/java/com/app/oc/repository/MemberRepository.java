package com.app.oc.repository;

import com.app.oc.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member findOne(String memberId) {
        return em.find(Member.class, memberId);
    }

    public List<Member> findByName(String memberId) {
        return em.createQuery("select m from Member m where m.memberId = :memberId",
                        Member.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
