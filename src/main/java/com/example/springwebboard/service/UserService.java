package com.example.springwebboard.service;

import com.example.springwebboard.dto.UserDto;
import com.example.springwebboard.entity.User;
import com.example.springwebboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto detail(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        return UserDto.create(user);
    }

    public UserDto createUser(UserDto dto) {
        // Entity로 변환
        User user = User.create(dto);

        // 이름, 패스워드, 닉네임이 비어있을경우 null 반환
        if (user.getNickname() == null) return null;
        if (user.getName() == null) return null;
        if (user.getPassword() == null) return null;

        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User created = userRepository.save(user);

        // Dto로 반환
        return UserDto.create(created);
    }

}
