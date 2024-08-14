package com.b1aboa.wedug.repository;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.Place;
import com.b1aboa.wedug.entity.PlaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByMediaInfo(MediaInfo mediaInfo);
    List<Place> findByMediaInfoAndPlaceCode(MediaInfo mediaInfo, PlaceInfo placeCode);
    List<Place> findByPlaceCode(PlaceInfo placeCode);
    @Query("SELECT p FROM Place p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.place) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Place> findByTitleOrPlaceContainingIgnoreCase(@Param("keyword") String keyword);

}