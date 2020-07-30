package com.example.website.config;


import com.example.website.model.entity.User;
import com.example.website.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Optional<User> userEntityOpt = userRepository.findByUsername(username);

        if (!userEntityOpt.isPresent()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(userEntityOpt.get());
    }

}
