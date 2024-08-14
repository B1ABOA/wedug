package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.Place;
import com.b1aboa.wedug.repository.MediaInfoRepository;
import com.b1aboa.wedug.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{

    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey;
    // Place 엔티티에 접근하기 위한 리포지토리
    private final PlaceRepository placeRepository;
    // MediaInfo 엔티티에 접근하기 위한 리포지토리
    private final MediaInfoRepository mediaInfoRepository;

    // Kakao Maps API 키
    @Override
    public String getKakaoMapsApiKey() {
        return kakaoMapsApiKey;
    }
    @Autowired
    public SearchServiceImpl(PlaceRepository placeRepository, MediaInfoRepository mediaInfoRepository) {
        this.placeRepository = placeRepository;
        this.mediaInfoRepository = mediaInfoRepository;
    }
    // 미디어 코드로 장소 검색
    @Override
    public List<PlaceDTO> searchPlacesByMediaType(String mediaType) {
        // mediaType으로 MediaInfo를 찾기
        Optional<MediaInfo> mediaInfo = mediaInfoRepository.findByMediaType(mediaType);

        // MediaInfo가 존재하면 해당 mediaCode로 Place를 검색하고 DTO로 변환.
        return mediaInfo
                .map(info -> placeRepository.findByMediaCode(info)
                        .stream()
                        .map(this::convertToPlaceDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }


    private PlaceDTO convertToPlaceDTO(Place place) {
        PlaceDTO dto = new PlaceDTO();
//        dto.setFilmId(place.getFilmId());
        dto.setTitle(place.getTitle());
        dto.setPlace(place.getPlace());
        dto.setPlaceDescribe(place.getPlaceDescribe());
        dto.setAddress(place.getAddress());
        dto.setLatitude(place.getLatitude());
        dto.setLongitude(place.getLongitude());
        dto.setBusinessHour(place.getBusinessHour());
        dto.setBreakTime(place.getBreakTime());
        return dto;
    }
}
