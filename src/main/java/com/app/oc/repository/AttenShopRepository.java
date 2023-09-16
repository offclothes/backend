package com.app.oc.repository;

import com.app.oc.entity.AttenItem;
import com.app.oc.entity.AttenShop;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttenShopRepository extends JpaRepository<AttenShop, Long> {

    /**
     * 관심 매장 조회 쿼리
     * 
     * @param id
     * @return
     */
    @EntityGraph(attributePaths = { "shoppingMal" })
    @Query("select a from AttenShop a where a.member.memberId=:id order by a.attens_seq desc")
    List<AttenShop> findAttenShop(@Param("id") String id);

    /**
     * 관심 상품 조회 쿼리
     * 
     * @param id
     * @return
     */
    @EntityGraph(attributePaths = { "item" })
    @Query("select a from AttenItem a where a.member.memberId=:id order by a.aitem_seq desc")
    List<AttenItem> findAttenItem(@Param("id") String id);

}
