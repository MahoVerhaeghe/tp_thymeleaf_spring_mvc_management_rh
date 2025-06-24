package com.example.demo.service.impl;

import com.example.demo.model.Absence;
import com.example.demo.repository.AbsenceRepository;
import com.example.demo.service.AbsenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;

    public AbsenceServiceImpl(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    @Override
    public Absence saveAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    @Override
    public List<Absence> getAbsencesByEmployeeId(Long employeeId) {
        return absenceRepository.findAll().stream()
                .filter(a -> a.getEmployee().getId().equals(employeeId))
                .toList();
    }

}