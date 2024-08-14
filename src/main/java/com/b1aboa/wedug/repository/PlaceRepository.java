package com.b1aboa.wedug.repository;

import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    // MediaInfo 객체로 Place 엔티티 목록을 찾는 메서드
    List<Place> findByMediaCode(MediaInfo mediaCode);
}