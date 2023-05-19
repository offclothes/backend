package com.app.oc.repository;

import com.app.oc.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,String> {

    @Query("select f from File f where f.item.itemId =:itemId")
    List<File> findByItem_Item_seq(@Param("itemId") Long itemId);


    @Query("select f from File f where f.item.itemId in :ids order by f.item.itemId")
    List<File> findFileIn(@Param("ids") List<Long> collectItemIds);
}
