package com.snitch.api.controller;

import com.snitch.api.service.EmployeeService;
import com.snitch.entities.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") long id) {
        return employeeService.getEmployee(id);
    }
}
