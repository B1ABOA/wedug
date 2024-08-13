package com.b1aboa.wedug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b1aboa.wedug.entity.Favorite;
import com.b1aboa.wedug.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Override
	public List<Favorite> showAllFavorites(String userId) {
		return favoriteRepository.showAllFavorites(userId);
	}

}
