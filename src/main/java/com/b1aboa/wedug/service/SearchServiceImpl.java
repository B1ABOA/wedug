package com.b1aboa.wedug.service;

import com.b1aboa.wedug.entity.MediaInfo;
import com.b1aboa.wedug.entity.Place;
import com.b1aboa.wedug.repository.MediaInfoRepository;
import com.b1aboa.wedug.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService{

    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey;
    private final PlaceRepository placeRepository;
    private final MediaInfoRepository mediaInfoRepository;

    @Override
    public String getKakaoMapsApiKey() {
        return kakaoMapsApiKey;
    }
    @Autowired
    public SearchServiceImpl(PlaceRepository placeRepository, MediaInfoRepository mediaInfoRepository) {
        this.placeRepository = placeRepository;
        this.mediaInfoRepository = mediaInfoRepository;
    }

    @Override
    public List<Place> searchPlacesByMediaCode(long mediaCode) {
        Optional<MediaInfo> mediaInfo = mediaInfoRepository.findById(mediaCode);
        return mediaInfo.map(placeRepository::findByMediaCode).orElse(List.of());
    }
}
