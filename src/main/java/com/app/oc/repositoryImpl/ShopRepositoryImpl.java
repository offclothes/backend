package com.app.oc.repositoryImpl;

import com.app.oc.entity.Item;
import com.app.oc.entity.Member;
import com.app.oc.entity.ShoppingMal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;




@Repository
public class ShopRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public void save(ShoppingMal shoppingmal) {
        em.persist(shoppingmal);
    }

    public void saveMember(Member member) { em.persist(member); }

    public Member findMemberByMemberId(String memberId) {
       return em.find(Member.class, memberId);
    }

    /**
     * shopId로 매장 정보 조회
     * @param shopId
     * @return
     */
    public ShoppingMal findByShopId(Long shopId) {
        return em.find(ShoppingMal.class, shopId); //이게 가능할 지?
    }
}
