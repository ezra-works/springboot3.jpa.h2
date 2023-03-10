/*
 * Copyright (c) 2023. Medavakkam, All rights reserved.
 */

package com.example.springboot3.jpa.h2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private REL relation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public REL getRelation() {
        return relation;
    }

    public void setRelation(REL relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", relation=" + relation +
                '}';
    }

    public enum REL {
        DAD, MOM, Son, Daughter
    }
}
