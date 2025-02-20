package com.hello.animalChat.domain;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    
    private int price;

    private String description;
}
