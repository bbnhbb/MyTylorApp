package com.mytylor.app.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity @Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            targetEntity = Customer.class
    ) @ToString.Exclude
    private List<Customer> customerList;

}
