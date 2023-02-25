package com.monitor.sensors.controller;

import com.monitor.sensors.dto.SensorReqDto;
import com.monitor.sensors.dto.SensorRespDto;
import com.monitor.sensors.mapper.SensorMapper;
import com.monitor.sensors.model.Sensor;
import com.monitor.sensors.model.Type;
import com.monitor.sensors.model.Unit;
import com.monitor.sensors.service.SensorService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    private final SensorMapper mapper;

    @GetMapping
    public String findAll(Model model, @RequestParam(required = false) String name,
                          @RequestParam(defaultValue = "0") @Min(0) Integer page,
                          @RequestParam(defaultValue = "4") @Min(1) Integer count,
                          Authentication authentication) {
        PageRequest pageRequest = PageRequest.of(page, count);
        sensorService.setModelIfAdmin(authentication, model);
        if (name != null && !name.isEmpty()) {
            List<SensorRespDto> sensorsDto = sensorService
                    .findAllByNameContains(name, pageRequest).stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            sensorService.setSensorsAndPaginationToModelIfNameContains(
                    name, sensorsDto, pageRequest, model);
        } else {
            List<SensorRespDto> sensorsDto = sensorService.findAll(pageRequest).stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            sensorService.setSensorsAndPaginationToModel(sensorsDto, pageRequest, model);
        }
        return "all-sensors";
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model, Authorization authorization) {
        model.addAttribute("sensor", mapper.toDto(sensorService.findById(id)));
        return "one-sensor";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("sensor") Sensor sensor, Model model) {
        setUnitsAndTypesToModel(model);
        return "create-update-sensor";
    }

    @PostMapping
    public String create(@ModelAttribute("sensor") @Valid SensorReqDto sensorReqDto,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            hasErrors(bindingResult, model);
            return "create-update-sensor";
        }
        try {
            sensorService.save(mapper.toModel(sensorReqDto));
        } catch (DataIntegrityViolationException e) {
            nameIsNotUnique(bindingResult, model);
            return "create-update-sensor";
        }
        return "redirect:/sensors";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("sensor", mapper.toDto(sensorService.findById(id)));
        setUnitsAndTypesToModel(model);
        return "create-update-sensor";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("sensor") @Valid SensorReqDto sensorReqDto,
                         BindingResult bindingResult, Model model, @PathVariable Long id) {
        hasErrors(bindingResult, model);
        if (bindingResult.hasErrors()) {
            hasErrors(bindingResult, model);
            return "create-update-sensor";
        }
        try {
            sensorService.update(id, mapper.toModel(sensorReqDto));
        } catch (DataIntegrityViolationException e) {
            nameIsNotUnique(bindingResult, model);
            return "create-update-sensor";
        }
        return "redirect:/sensors";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        sensorService.deleteById(id);
        return "redirect:/sensors";
    }

    private void setUnitsAndTypesToModel(Model model) {
        model.addAttribute("units", Unit.values());
        model.addAttribute("types", Type.values());
    }

    private void hasErrors(BindingResult bindingResult, Model model) {
        model.addAttribute("errors", bindingResult.getAllErrors());
        setUnitsAndTypesToModel(model);
    }

    private void nameIsNotUnique(BindingResult bindingResult, Model model) {
        bindingResult.addError(new FieldError("sensor", "name", "Name should be unique"));
        model.addAttribute("errors", bindingResult.getAllErrors());
        setUnitsAndTypesToModel(model);
    }
}
