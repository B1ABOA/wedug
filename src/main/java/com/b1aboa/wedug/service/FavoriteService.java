package com.b1aboa.wedug.service;

import java.util.List;

import com.b1aboa.wedug.dto.FavoriteDTO;
import com.b1aboa.wedug.entity.Favorite;

public interface FavoriteService {

    // 사용자 즐겨찾기 장소 목록
    List<FavoriteDTO> showAllFavorites(String userId);

    // 즐겨찾기 해제
    int deleteUserFavorite(String userId, long placeId);
}
