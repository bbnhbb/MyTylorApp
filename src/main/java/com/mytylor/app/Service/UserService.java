package com.mytylor.app.Service;

import com.mytylor.app.entity.User;
import com.mytylor.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public User registerUser(User user) {
        return userRepo.save(user);
    }
}
