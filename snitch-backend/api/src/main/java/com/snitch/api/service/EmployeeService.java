package com.snitch.api.service;

import com.snitch.api.viewmodels.EmployeeVM;
import com.snitch.entities.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(Long id);
    List<EmployeeVM> getEmployeeList();
}
