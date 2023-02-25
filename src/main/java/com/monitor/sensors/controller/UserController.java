package com.monitor.sensors.controller;

import com.monitor.sensors.config.SecurityConfig;
import com.monitor.sensors.model.Role;
import com.monitor.sensors.model.User;
import com.monitor.sensors.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SecurityConfig config;

    @GetMapping("/inject")
    @ResponseBody
    public String addViewerAndAdmin() {
        User viewer = new User();
        viewer.setLogin("viewer");
        viewer.setPassword(config.passwordEncoder().encode("1234"));
        viewer.setRoles(Set.of(Role.VIEWER));

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword(config.passwordEncoder().encode("1234"));
        admin.setRoles(Set.of(Role.ADMINISTRATOR));

        userService.save(viewer);
        userService.save(admin);

        return "login: admin, password: 1234 and login: viewer, password: 1234";
    }
}
