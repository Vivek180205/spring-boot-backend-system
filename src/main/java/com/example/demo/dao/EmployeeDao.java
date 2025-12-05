package com.example.demo.dao;

import com.example.demo.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();
    Employee findById(int id);
    int updateByValue(String name, String mobileNo);
}
