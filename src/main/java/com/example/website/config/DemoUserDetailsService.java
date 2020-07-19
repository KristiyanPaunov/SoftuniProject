package com.example.website.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.website.model.entity.Role;
import com.example.website.model.entity.User;
import com.example.website.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
//public class DemoUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private  UserRepository userRepository;
//
//    public DemoUserDetailsService() {
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//
//        Optional<User> userEntityOpt = userRepository.findByUsername(username);
//        return userEntityOpt.
//                map(this::map).
//                orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
//    }
//
//    private UserDetails map(User userEntity) {
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(this.map(userEntity.getRole()));
//        return new org.springframework.security.core.userdetails.User(
//                userEntity.getUsername(),
//                userEntity.getPassword(),
//                roles
//        );
//    }
//
//    private GrantedAuthority map(Role authorityEntity) {
//        return new SimpleGrantedAuthority(authorityEntity.name());
//    }
//}