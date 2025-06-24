package com.example.demo.controller;

import com.example.demo.model.Absence;
import com.example.demo.model.Conges;
import com.example.demo.model.Employee;
import com.example.demo.service.AbsenceService;
import com.example.demo.service.CongesService;
import com.example.demo.service.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CongesService congesService;
    private final AbsenceService absenceService;
    public EmployeeController(EmployeeService employeeService, CongesService congesService, AbsenceService absenceService){
        this.employeeService = employeeService;
        this.congesService = congesService;
        this.absenceService = absenceService;
    }

    @GetMapping
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/list";
    }

    @GetMapping("/view/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("conges", congesService.getCongesByEmployeeId(id));
        model.addAttribute("absences", absenceService.getAbsencesByEmployeeId(id));
        return "employees/view";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null) return "redirect:/employees";
        model.addAttribute("employee", employee);
        return "employees/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee){
        employee.setId(id);
        employeeService.saveEmployee(employee);
        return "redirect:/employees/view/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/add";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @PostMapping("/{id}/conges")
    public String addConges(@PathVariable Long id,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp != null) {
            Conges c = new Conges();
            c.setDateDebut(dateDebut);
            c.setDateFin(dateFin);
            c.setEmployee(emp);
            congesService.saveConges(c);
        }
        return "redirect:/employees/view/" + id;
    }

    @PostMapping("/{id}/absences")
    public String addAbsence(@PathVariable Long id,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate jourAbsence) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp != null) {
            Absence a = new Absence();
            a.setJourAbsence(jourAbsence);
            a.setEmployee(emp);
            absenceService.saveAbsence(a);
        }
        return "redirect:/employees/view/" + id;
    }
}