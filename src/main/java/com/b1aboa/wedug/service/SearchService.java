package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.PlaceDTO;
import com.b1aboa.wedug.entity.Place;
import org.springframework.beans.factory.annotation.Value;


import java.util.List;

public interface SearchService {
    // Kakao Maps API 키를 반환하는 메서드
    String getKakaoMapsApiKey();

    List<PlaceDTO> searchPlacesByMediaType(String mediaType);
}