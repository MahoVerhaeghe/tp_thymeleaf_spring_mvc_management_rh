package com.example.demo.service;

import com.example.demo.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    void deleteEmployee(Long id);
    void addConges(Long employeeId, LocalDate dateDebut, LocalDate dateFin);
    void addAbsence(Long employeeId, LocalDate jourAbsence);
}