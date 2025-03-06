package com.hello.animalChat.domain;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.*;
import com.hello.animalChat.Enum.*;
import com.hello.animalChat.dto.controller.RequestMemberSettingChangeDto;;


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
    private String name;
    @Column(length = 4)
    private String mbti;
    @Column(length = 20)
    private String animal;
    private char gender;
    private LocalDateTime create_at;

    @OneToMany(mappedBy="receiver")
    private List<Message> receivers = new ArrayList<>();

    public Member(){}

    public Member(LoginType loginType, String email, String password,String name ,String mbti, String animal, char gender,
            LocalDateTime create_at) {
        this.loginType = loginType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.mbti = mbti;
        this.animal = animal;
        this.gender = gender;
        this.create_at = create_at;
    }

   public void changeMemberPW(String password){
        this.password=password;
   }

   public void changeMemberSetting(RequestMemberSettingChangeDto dto){
        this.mbti=dto.getMbti();
        this.animal=dto.getAnimal();
        this.gender=dto.getGender();
   }
}

