package com.example.website.web;

import com.example.website.config.MyUserDetails;
import com.example.website.model.entity.User;
import com.example.website.model.repository.UserRepository;
import com.example.website.model.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String userName = "";
        HttpSession session = request.getSession();
        Collection<GrantedAuthority> authorities = null;
        if (authentication.getPrincipal() instanceof Principal) {
            userName = ((Principal) authentication.getPrincipal()).getName();
            session.setAttribute("role", "none");
        } else {
            MyUserDetails userSpringSecu = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            session.setAttribute("role", String.valueOf(userSpringSecu.getAuthorities().iterator().next().getAuthority()));
            User userEntity = userRepository.findByUsername(userSpringSecu.getUsername()).get();
            session.setAttribute("user", modelMapper.map(userEntity,UserServiceModel.class));
        }

        response.sendRedirect("/viewProducts");
    }
}
