package com.mytylor.app.deleted;

import com.mytylor.app.entity.User;
import com.mytylor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityUserImpl  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userRepository.findByUsername(username);
        if (user.get(0) == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new SecurityUser(user.get(0));
    }
}
