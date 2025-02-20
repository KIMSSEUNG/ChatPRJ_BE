package com.hello.animalChat.domain;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.*;
import com.hello.animalChat.Enum.*;;


@Entity
@Getter
@Table(name ="member")
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    
    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;

    @Column(unique = true)
    private String email;
    private String password;
    @Column(length = 4)
    private String mbti;
    @Column(length = 20)
    private String animal;
    private char gender;
    private LocalDateTime create_at;

    @OneToMany(mappedBy="receiver")
    private List<Message> receivers = new ArrayList<>();

    public Member(){}

    public Member(Long id , LoginType loginType, String email, String password, String mbti, String animal, char gender,
            LocalDateTime create_at) {
        this.id =id;
        this.loginType = loginType;
        this.email = email;
        this.password = password;
        this.mbti = mbti;
        this.animal = animal;
        this.gender = gender;
        this.create_at = create_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return gender == member.gender && Objects.equals(id, member.id) && loginType == member.loginType && Objects.equals(email, member.email) && Objects.equals(password, member.password) && Objects.equals(mbti, member.mbti) && Objects.equals(animal, member.animal) && Objects.equals(create_at, member.create_at) && Objects.equals(receivers, member.receivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginType, email, password, mbti, animal, gender, create_at, receivers);
    }
}

