package com.b1aboa.wedug.repository;

import com.b1aboa.wedug.entity.MediaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaInfoRepository extends JpaRepository<MediaInfo, Long> {

    // mediaType으로 MediaInfo를 찾는 메서드
    Optional<MediaInfo> findByMediaCode(long mediaCode);
}
