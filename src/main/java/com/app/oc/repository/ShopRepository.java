package com.app.oc.repository;

import com.app.oc.entity.ShoppingMal;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<ShoppingMal, Long> {

    ShoppingMal findByMember(String memberId);

    @Query("SELECT s FROM ShoppingMal s WHERE s.member.memberId = :memberId ORDER BY s.shopId")
    List<ShoppingMal> findShoppingMalByMember(@Param("memberId") String memberId);

}