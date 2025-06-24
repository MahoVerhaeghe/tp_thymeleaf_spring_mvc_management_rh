package com.example.demo.service.impl;

import com.example.demo.model.Absence;
import com.example.demo.model.Conges;
import com.example.demo.model.Employee;
import com.example.demo.repository.AbsenceRepository;
import com.example.demo.repository.CongesRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CongesRepository congesRepository;
    private final AbsenceRepository absenceRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CongesRepository congesRepository, AbsenceRepository absenceRepository) {
        this.employeeRepository = employeeRepository;
        this.congesRepository = congesRepository;
        this.absenceRepository = absenceRepository;
    }

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    @Override
    public void addConges(Long employeeId, LocalDate dateDebut, LocalDate dateFin) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employé introuvable avec ID: " + employeeId));

        Conges conges = new Conges();
        conges.setDateDebut(dateDebut);
        conges.setDateFin(dateFin);
        conges.setEmployee(employee);

        congesRepository.save(conges);
    }

    @Override
    public void addAbsence(Long employeeId, LocalDate jourAbsence) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employé introuvable avec ID: " + employeeId));

        Absence absence = new Absence();
        absence.setJourAbsence(jourAbsence);
        absence.setEmployee(employee);

        absenceRepository.save(absence);
    }
}