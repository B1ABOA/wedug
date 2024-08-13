package com.b1aboa.wedug.repository;

import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByMediaCode(MediaInfo mediaCode);
}