//package com.b1aboa.wedug.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@Entity
//@Table(name = "filming_location")
//public class Place {
//
//    @Id
//    @Column(name = "film_id")
//    private Long filmId;
//
//    @Column(name = "media_code", nullable = false)
//    private Long mediaCode;
//
//    @Column(name = "place_code", nullable = false)
//    private Long placeCode;
//
//    @Column(name = "title", length = 100, nullable = false)
//    private String title;
//
//    @Column(name = "place", length = 100, nullable = false)
//    private String place;
//
//    @Column(name = "place_describe", length = 2000, nullable = false)
//    private String placeDescribe;
//
//    @Column(name = "business_hour", length = 300)
//    private String businessHour;
//
//    @Column(name = "break_time", length = 80)
//    private String breakTime;
//
//    @Column(name = "closed_day", length = 100)
//    private String closedDay;
//
//    @Column(name = "address", length = 150, nullable = false)
//    private String address;
//
//    @Column(name = "latitude", nullable = false, precision = 12, scale = 9)
//    private Double latitude;
//
//    @Column(name = "longitude", nullable = false, precision = 12, scale = 9)
//    private Double longitude;
//
//    @Column(name = "phone_number", length = 20)
//    private String phoneNumber;
//}