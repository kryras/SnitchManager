package com.snitch.api.service;

import com.snitch.api.repository.EmployeeRepository;
import com.snitch.api.viewmodels.EmployeeVM;
import com.snitch.entities.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public List<EmployeeVM> getEmployeeList() {
        List<EmployeeVM> result = new ArrayList<>();
        for(Employee emp: employeeRepository.findAll()){
            result.add(new EmployeeVM(emp));
        }
        return result;
    }
}
