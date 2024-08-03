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
@Table(name = "dept")
public class Dept {

    @Id
    @Column(name = "deptno")
    private int deptno;

    @Column(name = "dname", length = 20)
    private String dname;

    @Column(name = "loc", length = 20)
    private String loc;
}
