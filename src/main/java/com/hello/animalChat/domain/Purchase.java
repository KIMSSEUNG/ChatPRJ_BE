package com.hello.animalChat.domain;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Purchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int quantity;
    private int totalPrice;
    private LocalDateTime purchase_at;

}
