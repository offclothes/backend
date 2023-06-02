package com.app.oc.repository;



import com.app.oc.entity.ShoppingMal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<ShoppingMal,Long> {

//    Optional<ShoppingMal> findById(Long id);

    ShoppingMal findByMember(String memberId);
}
