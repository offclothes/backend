package com.app.oc.repository;

import com.app.oc.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> ,ItemRepositoryCustom{

//    @Query("select i from Item i where i.shoppingMal.shopId =:id")
//    List<Item> findByshopItem(@Param("id") Long id);
}
