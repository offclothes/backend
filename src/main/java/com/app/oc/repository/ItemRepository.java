package com.app.oc.repository;



import com.app.oc.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> ,ItemRepositoryCustom{

//    @Query("select i from Item i where i.shoppingMal.shopId =:id")
//    List<Item> findByshopItem(@Param("id") Long id);
}
