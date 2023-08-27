package com.app.oc.repository;

import com.app.oc.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Query("select distinct r.topRegion from Region r")
    List<String> findTop();

    @Query("select distinct r.middleRegion from Region r where r.topRegion = :topRegion order by r.middleRegion asc ")
    List<String> findMid(@Param("topRegion") String topRegion);

    @Query("select distinct r.dongRegion from Region r where r.topRegion = :topRegion and r.middleRegion = :middleRegion order by r.dongRegion asc")
    List<String> findDong(@Param("topRegion") String topRegion, @Param("middleRegion") String middleRegion);

}
