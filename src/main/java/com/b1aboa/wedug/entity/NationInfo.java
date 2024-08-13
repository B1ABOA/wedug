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
@Table(name = "nation_info")
public class NationInfo {

    @Id
    private long nationCode;

    @Column(name = "en_name")
    private String enName;

    @Column(name = "ko_name")
    private String koName;
}
