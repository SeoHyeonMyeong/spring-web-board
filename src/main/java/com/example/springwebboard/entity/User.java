package com.example.springwebboard.entity;

import com.example.springwebboard.dto.UserDto;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String name;

    @Column
    private String password;

    @Column
    private String nickname;

    public static User create(UserDto dto){
        return new User(null,
                dto.getName(),
                dto.getPassword(),
                dto.getNickname());
    }
}
