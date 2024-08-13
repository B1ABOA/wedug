package com.b1aboa.wedug.service;

import com.b1aboa.wedug.entity.Place;
import org.springframework.beans.factory.annotation.Value;


import java.util.List;

public interface SearchService {

//    @Value("${kakao.maps.api.key}")
//    private String kakaoMapsApiKey;

    String getKakaoMapsApiKey();
//    public String getKakaoMapsApiKey() {
//        return kakaoMapsApiKey;
//    }
    List<Place> searchPlacesByMediaCode(long mediaCode);
}