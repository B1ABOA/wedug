package com.b1aboa.wedug.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "filming_location")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_seq")
    @SequenceGenerator(name = "film_id_seq", sequenceName = "film_id_seq", allocationSize = 1)
    @Column(name = "film_id")
    private Long filmId;

    @ManyToOne
    @JoinColumn(name = "media_code")
    private MediaInfo mediaCode;

    @ManyToOne
    @JoinColumn(name = "place_code")
    private PlaceInfo placeCode;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "place", length = 100, nullable = false)
    private String place;

    @Column(name = "place_describe", length = 2000, nullable = false)
    private String placeDescribe;

    @Column(name = "business_hour", length = 300)
    private String businessHour;

    @Column(name = "break_time", length = 80)
    private String breakTime;

    @Column(name = "closed_day", length = 100)
    private String closedDay;

    @Column(name = "address", length = 150, nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;


    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
