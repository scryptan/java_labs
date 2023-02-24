package com.scryptan.springlab.service;

import com.scryptan.springlab.entity.User;
import com.scryptan.springlab.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null)
            throw new UsernameNotFoundException("Invalid email or password");

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map((role) -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }
}
