package com.mytylor.app.repository;


import com.mytylor.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
