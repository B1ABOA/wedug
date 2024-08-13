package com.b1aboa.wedug.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "place_info")
public class PlaceInfo {

    @Id
    private long placeCode;

    @Column(name = "place_type", nullable = false)
    private String placeType;
}
