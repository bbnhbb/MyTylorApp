package com.mytylor.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Dress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(nullable = true)
    @ToString.Exclude
    private Customer customer;

    private Long msrSholder;
    private Long msrWaist;
    private Long msrBelly;
    private Long msrArm;
    private Long msrLeg;

}
