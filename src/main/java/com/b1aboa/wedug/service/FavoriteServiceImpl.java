package com.b1aboa.wedug.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.b1aboa.wedug.dto.FavoriteDTO;
import com.b1aboa.wedug.entity.Favorite;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b1aboa.wedug.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<FavoriteDTO> showAllFavorites(String userId) {
        return favoriteRepository.showAllFavorites(userId).stream()
                .map(f -> FavoriteDTO.builder()
                        .favoriteId(f.getFavoriteId())
                        .userId(f.getUserId().getUserId())
                        .filmId(f.getFilmId().getFilmId())
                        .title(f.getFilmId().getTitle())
                        .place(f.getFilmId().getPlace())
                        .placeDescribe(f.getFilmId().getPlaceDescribe())
                        .address(f.getFilmId().getAddress())
                        .phoneNumber(f.getFilmId().getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public int deleteUserFavorite(String userId, long placeId) {
        return favoriteRepository.deleteUserFavorite(userId, placeId);
    }
}
