package com.example.springwebboard.repository;

import com.example.springwebboard.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
