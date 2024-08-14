package com.b1aboa.wedug.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
