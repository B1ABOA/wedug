package com.b1aboa.wedug.repository;

import com.b1aboa.wedug.entity.PlaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaceInfoRepository extends JpaRepository<PlaceInfo, Long> {

}
