package com.iset.produit.entities;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    public Integer getId() {
        return id;
    }

    public Role() {
    }

    public Role( String name) {
        super();
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
}