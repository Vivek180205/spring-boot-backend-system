package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Cacheable(value = "employee", key ="#id")
    @Override
    public Employee findById(int id) {
        Employee emp = employeeDao.findById(id);
        return emp;
    }

    @Override
    public String updateByValue(Employee e) {

        int row  =  employeeDao.updateByValue(e);
        if(row> 0){
            return "Successfully Updated";
        }else
            return "No Record Updated";
    }

    @Override
    public boolean deleteByValue(int id) {
        return employeeDao.deleteByvalue(id)>0;
    }
}
