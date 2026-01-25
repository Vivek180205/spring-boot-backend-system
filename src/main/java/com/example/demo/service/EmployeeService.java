package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    String updateByValue(Employee e);
    boolean deleteByValue(int id);
}
