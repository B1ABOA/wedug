package com.b1aboa.wedug.repository;

import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.PlaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceInfoRepository extends JpaRepository<PlaceInfo, Long> {

}
