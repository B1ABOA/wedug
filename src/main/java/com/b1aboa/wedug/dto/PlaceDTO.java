package com.b1aboa.wedug.dto;

import lombok.Data;

@Data
public class PlaceDTO {
    private Long filmId;
    private String title;
    private String place;
    private String placeDescribe;
    private String businessHour;
    private String breakTime;
    private String closedDay;
    private String address;
    private Double latitude;
    private Double longitude;
    private String phoneNumber;
}
