package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.Place;
import com.b1aboa.wedug.repository.MediaInfoRepository;
import com.b1aboa.wedug.repository.PlaceRepository;
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
    private String kakaoMapsApiKey;

    private final PlaceRepository placeRepository;
    private final MediaInfoRepository mediaInfoRepository;

    @Autowired
    public SearchServiceImpl(PlaceRepository placeRepository, MediaInfoRepository mediaInfoRepository) {
        this.placeRepository = placeRepository;
        this.mediaInfoRepository = mediaInfoRepository;
    }

    @Override
    public String getKakaoMapsApiKey() {
        return kakaoMapsApiKey;
    }


    @Override
    public List<PlaceDTO> searchPlacesByMediaCode(long mediaCode) {
        logger.info("Searching places for media code: {}", mediaCode);

        Optional<MediaInfo> mediaInfo = mediaInfoRepository.findById(mediaCode);

        if (mediaInfo.isPresent()) {
            List<Place> places = placeRepository.findByMediaInfo(mediaInfo.get());
            logger.info("Found {} places", places.size());
            return places.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            logger.warn("No MediaInfo found for media code: {}", mediaCode);
            return Collections.emptyList();
        }
    }

    private PlaceDTO convertToDTO(Place place) {
        PlaceDTO dto = new PlaceDTO();
        dto.setFilmId(place.getFilmId());
        dto.setTitle(place.getTitle());
        dto.setPlace(place.getPlace());
        dto.setPlaceDescribe(place.getPlaceDescribe());
        dto.setBusinessHour(place.getBusinessHour());
        dto.setBreakTime(place.getBreakTime());
        dto.setClosedDay(place.getClosedDay());
        dto.setAddress(place.getAddress());
        dto.setLatitude(place.getLatitude());
        dto.setLongitude(place.getLongitude());
        dto.setPhoneNumber(place.getPhoneNumber());
        return dto;
    }
}