package com.piphub.springjwtauthentication.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "products")
@Entity
@ToString
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String nameKh;
    private double price;
    private double cost;
    private double discount;
    private String status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
