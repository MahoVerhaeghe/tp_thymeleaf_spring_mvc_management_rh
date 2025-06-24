package com.example.demo.service.impl;

import com.example.demo.model.Conges;
import com.example.demo.repository.CongesRepository;
import com.example.demo.service.CongesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongesServiceImpl implements CongesService {

    private final CongesRepository congesRepository;

    public CongesServiceImpl(CongesRepository congesRepository) {
        this.congesRepository = congesRepository;
    }

    @Override
    public Conges saveConges(Conges conges) {
        return congesRepository.save(conges);
    }

    @Override
    public List<Conges> getCongesByEmployeeId(Long employeeId) {
        return congesRepository.findAll().stream()
                .filter(c -> c.getEmployee().getId().equals(employeeId))
                .toList();
    }

}