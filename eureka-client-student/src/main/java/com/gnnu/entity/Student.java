package com.gnnu.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sname")
    private String sname;
    @Column(name = "snum")
    private String snum;
    @Column(name = "score")
    private Double score;
}
