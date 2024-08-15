package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.Place;
import com.b1aboa.wedug.entity.PlaceInfo;
import com.b1aboa.wedug.repository.MediaInfoRepository;
import com.b1aboa.wedug.repository.PlaceInfoRepository;
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
public class PlaceServiceImpl implements PlaceService {

    private static final Logger logger = LoggerFactory.getLogger(PlaceServiceImpl.class);

    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey; // Kakao Maps API 키를 주입

    private final PlaceRepository placeRepository; // Place 엔티티를 위한 레포지토리
    private final MediaInfoRepository mediaInfoRepository; // MediaInfo 엔티티를 위한 레포지토리
    private final ModelMapper modelMapper; // 엔티티와 DTO 간의 변환을 위한 ModelMapper

    private final PlaceInfoRepository placeInfoRepository;
    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository, MediaInfoRepository mediaInfoRepository, PlaceInfoRepository placeInfoRepository, ModelMapper modelMapper) {
        this.placeRepository = placeRepository;
        this.mediaInfoRepository = mediaInfoRepository;
        this.placeInfoRepository = placeInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String getKakaoMapsApiKey() {
        return kakaoMapsApiKey; // Kakao Maps API 키 반환
    }

    @Override
    public List<PlaceDTO> getAllPlaces() {
        logger.info("Fetching all places");
        List<Place> places = placeRepository.findAll();
        logger.info("Found {} places", places.size());

        return places.stream()
                .map(place -> modelMapper.map(place, PlaceDTO.class))
                .collect(Collectors.toList());
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

    @Override
    public List<PlaceDTO> searchPlacesByMediaCodeAndPlace(long mediaCode, long placeCode) {
        logger.info("Searching places for media code & placeCode: {} , {}", mediaCode, placeCode);

        Optional<MediaInfo> mediaInfoOptional = mediaInfoRepository.findById(mediaCode);
        Optional<PlaceInfo> placeInfoOptional = placeInfoRepository.findById(placeCode);

        if (mediaInfoOptional.isPresent() && placeInfoOptional.isPresent()) {
            MediaInfo mediaInfo = mediaInfoOptional.get();
            PlaceInfo placeInfo = placeInfoOptional.get();

            List<Place> places = placeRepository.findByMediaInfoAndPlaceCode(mediaInfo, placeInfo);
            logger.info("Found {} places for media code {} and place code {}", places.size(), mediaCode, placeCode);

            return places.stream()
                    .map(place -> modelMapper.map(place, PlaceDTO.class))
                    .collect(Collectors.toList());
        } else {
            logger.warn("No MediaInfo or PlaceInfo found for media code: {} and place code: {}", mediaCode, placeCode);
            return Collections.emptyList();
        }
    }

    @Override
    public List<PlaceDTO> searchPlacesByPlaceCode(long placeCode) {
        logger.info("Searching places for place code: {}", placeCode);

        Optional<PlaceInfo> placeInfoOptional = placeInfoRepository.findById(placeCode);

        if (placeInfoOptional.isPresent()) {
            PlaceInfo placeInfo = placeInfoOptional.get();
            List<Place> places = placeRepository.findByPlaceCode(placeInfo);
            logger.info("Found {} places for place code {}", places.size(), placeCode);

            return places.stream()
                    .map(place -> modelMapper.map(place, PlaceDTO.class))
                    .collect(Collectors.toList());
        } else {
            logger.warn("No PlaceInfo found for place code: {}", placeCode);
            return Collections.emptyList();
        }
    }


    @Override
    public List<PlaceDTO> searchPlacesByKeyword(String keyword) {
        logger.info("Searching places with keyword: {}", keyword);

        List<Place> places = placeRepository.findByTitleOrPlaceContainingIgnoreCase(keyword);
        logger.info("Found {} places with keyword {}", places.size(), keyword);

        return places.stream()
                .map(place -> modelMapper.map(place, PlaceDTO.class))
                .collect(Collectors.toList());
    }

}
