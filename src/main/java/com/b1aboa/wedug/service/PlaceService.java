package com.b1aboa.wedug.service;

import com.b1aboa.wedug.dto.PlaceDTO;


import java.util.List;

public interface PlaceService {
    // Kakao Maps API 키를 반환하는 메서드
    String getKakaoMapsApiKey();
    List<PlaceDTO> getAllPlaces();
    List<PlaceDTO> searchPlacesByMediaCode(long mediaCode);
    List<PlaceDTO> searchPlacesByMediaCodeAndPlace(long mediaCode,long placeCode);
    List<PlaceDTO> searchPlacesByPlaceCode(long placeCode);
    List<PlaceDTO> searchPlacesByKeyword(String keyword);

}