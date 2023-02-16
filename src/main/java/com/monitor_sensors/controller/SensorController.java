package com.monitor_sensors.controller;

import com.monitor_sensors.dto.SensorReqDto;
import com.monitor_sensors.mapper.SensorMapper;
import com.monitor_sensors.model.Sensor;
import com.monitor_sensors.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    private final SensorMapper mapper;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("sensors", sensorService.findAll());
        return "all-sensors";
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("sensor", sensorService.findById(id));
        return "one-sensor";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("sensor") Sensor sensor) {
        return "create-sensor";
    }

    @PostMapping
    public String create(@ModelAttribute("sensor") Sensor sensor) {
        sensorService.save(sensor);
        return "redirect:/sensors";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("sensor", sensorService.findById(id));
        return "update-sensor";
    }

    @PostMapping("{id}")
    public String update(@ModelAttribute("sensor") Sensor sensor, @PathVariable Long id) {
        sensorService.update(id, sensor);
        return "redirect:/sensors";
    }

    @PostMapping("test/{id}")
    @ResponseBody
    public Sensor updateTest(@PathVariable Long id, @RequestBody SensorReqDto dto) {
        //return sensorService.update(id, sensor);
        Sensor sensor = mapper.toModel(dto);
        sensor.setId(id);
        return sensor;
    }

    @GetMapping("test")
    @ResponseBody
    public List<Sensor> all() {
        return sensorService.findAll();
    }
}
