package com.example.demo.service;

import com.example.demo.model.Conges;
import java.util.List;

public interface CongesService {
    Conges saveConges(Conges conges);
    List<Conges> getCongesByEmployeeId(Long employeeId);
}