package com.monitor.sensors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            com.monitor.sensors.model.User user = userService.findByLogin(login);
            return User.withUsername(user.getLogin())
                    .password(user.getPassword())
                    .authorities(user.getRoles()).build();
        } catch (RuntimeException e) {
            throw new UsernameNotFoundException("Invalid login or password");
        }
    }
}
