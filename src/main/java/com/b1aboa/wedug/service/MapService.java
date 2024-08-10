package com.b1aboa.wedug.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapService {

    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey;

    public String getKakaoMapsApiKey() {
        return kakaoMapsApiKey;
    }
}