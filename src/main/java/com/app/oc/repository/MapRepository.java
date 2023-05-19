package com.app.oc.repository;

import com.app.oc.dto.map.MapRequestDto;
import com.app.oc.dto.map.MapResponDto;
import com.app.oc.entity.ShoppingMal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MapRepository extends JpaRepository<ShoppingMal, Long> {
    @Query("select s.shopId, s.address.address1, s.address.address2 from ShoppingMal s where s.address.address1 LIKE :region")
    List<MapResponDto> findByAddressContaining(String region);
}
