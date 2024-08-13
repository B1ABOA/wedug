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
@Table(name = "media_info")
public class MediaInfo {

    @Id
    private long mediaCode;

    @Column(name = "media_type", nullable = false)
    private String mediaType;
}
