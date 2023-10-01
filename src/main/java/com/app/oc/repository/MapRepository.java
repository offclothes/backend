package com.app.oc.repository;


import com.app.oc.entity.ShoppingMal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MapRepository extends JpaRepository<ShoppingMal, Long> {

    List<ShoppingMal> findByAddress_Address1Containing(@Param("address1") String address1);
}
