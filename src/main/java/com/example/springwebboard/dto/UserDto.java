package com.example.springwebboard.dto;


import com.example.springwebboard.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDto {

    private String name;
    private String password;
    private String nickname;

    public static UserDto create(User entity) {
        return new UserDto(entity.getName(), entity.getPassword(), entity.getNickname());
    }
}
