package com.snitch.api.service;

import com.snitch.api.repository.EmployeeRepository;
import com.snitch.entities.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }
}
