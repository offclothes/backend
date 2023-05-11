package com.app.oc.repository;

import com.app.oc.entity.ShoppingMal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShoppingMal,Long> {

//    Optional<ShoppingMal> findById(Long id);

}
