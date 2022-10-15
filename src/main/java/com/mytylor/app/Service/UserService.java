package com.mytylor.app.Service;

import com.mytylor.app.config.UsernamePwdAuthenticationProvider;
import com.mytylor.app.entity.User;
import com.mytylor.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final UsernamePwdAuthenticationProvider authProvider;

    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public Boolean loginService(User user) {
        Authentication authenticate = authProvider.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        return authenticate.isAuthenticated();
    }

    public User findUserByName(String name) {
        User user = userRepo.findByUsername(name).get(0);
        return user;
    }
}
