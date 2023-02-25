package com.monitor.sensors.service;

import com.monitor.sensors.dto.SensorRespDto;
import com.monitor.sensors.model.Sensor;
import com.monitor.sensors.repository.SensorRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository repository;

    @Override
    public Sensor save(Sensor sensor) {
        return repository.save(sensor);
    }

    @Override
    public Sensor update(Long id, Sensor sensor) {
        sensor.setId(id);
        return save(sensor);
    }

    @Override
    public Sensor findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Sensor> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Sensor> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest).toList();
    }

    @Override
    public List<Sensor> findAllByNameContains(String namePart, PageRequest pageRequest) {
        return repository.findAllByNameContains(namePart, pageRequest);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Long countByNameContains(String name) {
        return repository.countByNameContains(name);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public void setModelIfAdmin(Authentication authentication, Model model) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRATOR"))) {
            model.addAttribute("admin", true);
        }
    }

    @Override
    public void setSensorsAndPaginationToModel(
            List<SensorRespDto> sensorsDto, PageRequest pageRequest, Model model) {
        Long total = count();
        PageImpl<SensorRespDto> sensors = new PageImpl<>(sensorsDto, pageRequest, total);
        model.addAttribute("sensors", sensors);
        model.addAttribute("total", total);
    }

    @Override
    public void setSensorsAndPaginationToModelIfNameContains(
            String name, List<SensorRespDto> sensorsDto, PageRequest pageRequest, Model model) {
        Long total = countByNameContains(name);
        PageImpl<SensorRespDto> sensors = new PageImpl<>(sensorsDto, pageRequest, total);
        model.addAttribute("sensors", sensors);
        model.addAttribute("total", total);
        model.addAttribute("name", name);
    }
}
