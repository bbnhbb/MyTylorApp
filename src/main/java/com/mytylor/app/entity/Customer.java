package com.mytylor.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn( nullable = true)
    @ToString.Exclude
    private User user;

    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            targetEntity = Dress.class
    ) @ToString.Exclude
    private List<Dress> dressList;
}
