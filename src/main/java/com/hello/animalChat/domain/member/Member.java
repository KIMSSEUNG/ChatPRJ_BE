package com.hello.animalChat.domain.member;
import java.time.LocalDateTime;

import com.hello.animalChat.domain.Message;
import com.hello.animalChat.dto.member.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import com.hello.animalChat.Enum.*;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;
;


@Entity
@Getter
@Table(name ="member")
@NoArgsConstructor // JPA용 기본 생성자
@AllArgsConstructor
@Builder
@ToString
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
    private int age;
    @Column(length = 4)
    private String mbti;
    @Column(length = 20)
    private String animal;
    private char gender;
    private LocalDateTime create_at;

    @OneToMany(mappedBy="receiver")
    private List<Message> receivers = new ArrayList<>();

    public  void setCreateAt(LocalDateTime createAt){
        this.create_at = createAt;
    }

    public void changeMemberPW(String password){
        this.password=password;
   }

    public void changeMemberSetting(MemberSettingChangeDto dto){
        this.mbti=dto.getMbti();
        this.animal=dto.getAnimal();
    }

}

