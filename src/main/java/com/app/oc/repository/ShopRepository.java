package com.app.oc.repository;

import com.app.oc.entity.ShoppingMal;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShoppingMal,Long> {



}
