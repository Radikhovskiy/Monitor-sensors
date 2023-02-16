package com.monitor_sensors.service;

import com.monitor_sensors.model.User;
import com.monitor_sensors.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find user by id: " + id));
    }

    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Can't find user by login: " + login));
    }
}
