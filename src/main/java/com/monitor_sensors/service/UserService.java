package com.monitor_sensors.service;

import com.monitor_sensors.model.User;

public interface UserService {
    User save(User user);

    void deleteById(Long id);

    User findById(Long id);

    User findByLogin(String login);
}
