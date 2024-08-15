package com.b1aboa.wedug.dto;

import com.b1aboa.wedug.entity.Place;
import com.b1aboa.wedug.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteDTO {
    private Long favoriteId;
    private String userId;
    private Long filmId;
    private String title;
    private String place;
    private String placeDescribe;
    private String address;
    private String phoneNumber;
}
