package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.Place;
import com.b1aboa.wedug.repository.MediaInfoRepository;
import com.b1aboa.wedug.repository.PlaceRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey; // Kakao Maps API 키를 주입

    private final PlaceRepository placeRepository; // Place 엔티티를 위한 레포지토리
    private final MediaInfoRepository mediaInfoRepository; // MediaInfo 엔티티를 위한 레포지토리
    private final ModelMapper modelMapper; // 엔티티와 DTO 간의 변환을 위한 ModelMapper

    @Autowired
    public SearchServiceImpl(PlaceRepository placeRepository, MediaInfoRepository mediaInfoRepository, ModelMapper modelMapper) {
        this.placeRepository = placeRepository;
        this.mediaInfoRepository = mediaInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String getKakaoMapsApiKey() {
        return kakaoMapsApiKey; // Kakao Maps API 키 반환
    }

    @Override
    public List<PlaceDTO> searchPlacesByMediaCode(long mediaCode) {
        logger.info("Searching places for media code: {}", mediaCode); // 로그에 mediaCode 기록

        // mediaCode를 기반으로 MediaInfo를 조회
        Optional<MediaInfo> mediaInfo = mediaInfoRepository.findById(mediaCode);

        if (mediaInfo.isPresent()) {
            // MediaInfo가 존재하면 해당 MediaInfo로 Place 리스트 조회
            List<Place> places = placeRepository.findByMediaInfo(mediaInfo.get());
            logger.info("Found {} places", places.size()); // 찾은 장소 수 로그에 기록

            // ModelMapper를 사용하여 Place 엔티티를 PlaceDTO로 변환
            return places.stream()
                    .map(place -> modelMapper.map(place, PlaceDTO.class))
                    .collect(Collectors.toList());
        } else {
            logger.warn("No MediaInfo found for media code: {}", mediaCode); // MediaInfo가 없으면 경고 로그
            return Collections.emptyList(); // 빈 리스트 반환
        }
    }
}
