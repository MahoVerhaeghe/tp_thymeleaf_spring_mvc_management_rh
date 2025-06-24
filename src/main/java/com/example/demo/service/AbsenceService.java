package com.example.demo.service;

import com.example.demo.model.Absence;
import java.util.List;

public interface AbsenceService {
    Absence saveAbsence(Absence absence);
    List<Absence> getAbsencesByEmployeeId(Long employeeId);
}