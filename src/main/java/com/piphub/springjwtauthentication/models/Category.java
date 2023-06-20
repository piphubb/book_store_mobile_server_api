package com.piphub.springjwtauthentication.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String nameKh;
    private String status;// ACT=Active/DEL=Delete
}
