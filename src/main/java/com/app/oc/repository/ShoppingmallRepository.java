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
public class ShoppingmallRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Shoppingmall shoppingmall) {
        em.persist(shoppingmall);
    }

    public void saveMember(Member member) { em.persist(member); }

    public Member findMemberByMemberId(String memberId) {
       return em.find(Member.class, memberId);
    }

    /**
     * 자기 매장에 등록한 의류 전체 조회 메서드
     * @param shopId
     * @return
     */
    public List<Item> findItemByShopId(Long shopId) {
        return em.createQuery("select m from Item m inner join m.shoppingmall s where s.shopId = :shopId",
                        Item.class)
                .setParameter("shopId", shopId)
                .getResultList();
    }

    /**
     * shopId로 매장 정보 조회
     * @param shopId
     * @return
     */
    public Shoppingmall findByShopId(Long shopId) {
        return em.find(Shoppingmall.class, shopId); //이게 가능할 지?
    }

}
