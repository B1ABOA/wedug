package com.b1aboa.wedug.service;

import java.util.List;

import com.b1aboa.wedug.entity.Favorite;

public interface FavoriteService {

	// 사용자 즐겨찾기 장소 목록
	List<Favorite> showAllFavorites(String userId);
}
