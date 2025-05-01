package com.hello.animalChat.domain;
import com.hello.animalChat.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PurchaseCnt {
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

    private int count;

    public PurchaseCnt(){}

    public PurchaseCnt(Product product, Member member, int count) {
        this.product = product;
        this.member = member;
        this.count = count;
    }

    
}
