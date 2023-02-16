package com.monitor_sensors.controller;

import com.monitor_sensors.config.SecurityConfig;
import com.monitor_sensors.model.Role;
import com.monitor_sensors.model.Sensor;
import com.monitor_sensors.model.User;
import com.monitor_sensors.service.SensorService;
import com.monitor_sensors.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class InjectController {
    private final UserService userService;
    private final SensorService sensorService;
    private final SecurityConfig config;

    @GetMapping("/inject")
    @ResponseBody
    public String injectUser() {
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

        Sensor sensor1 = new Sensor();
        sensor1.setName("Sensor1");

        Sensor sensor2 = new Sensor();
        sensor2.setName("Sensor2");

        sensorService.save(sensor1);
        sensorService.save(sensor2);

        return "Compleate!";
    }

//    @GetMapping("/inject2")
//    @ResponseBody
//    public String injectSensors() {
//        Sensor sensor1 = new Sensor();
//        sensor1.setName("Sensor1");
//
//        Sensor sensor2 = new Sensor();
//        sensor2.setName("Sensor2");
//
//        sensorService.save(sensor1);
//        sensorService.save(sensor2);
//
//        return "Good!";
//    }

    @GetMapping("/hi")
    @ResponseBody
    public String hi() {
        return "Hi!";
    }

    @GetMapping("/auth")
    @ResponseBody
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/viewer")
    @ResponseBody
    public String viewer() {
        return "viewer!";
    }
}
