package com.example.springwebboard.api;

import com.example.springwebboard.dto.UserDto;
import com.example.springwebboard.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @GetMapping("api/users/{id}")
    public ResponseEntity<UserDto> detail(@PathVariable Long id){
        UserDto dto = userService.detail(id);
        return (dto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(dto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserDto> create(UserDto dto){
        UserDto created = userService.createUser(dto);
        return (created != null) ?
            ResponseEntity.status(HttpStatus.CREATED).body(created) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
