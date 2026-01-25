package com.example.demo.service;

import com.example.demo.dao.EmployeeVideoDao;
import com.example.demo.model.EmployeeVideo;
import org.springframework.stereotype.Service;

@Service
public class EmployeeVideoService {

    private final EmployeeVideoDao employeeVideoDao;

    public EmployeeVideoService(EmployeeVideoDao employeeVideoDao) {
        this.employeeVideoDao = employeeVideoDao;
    }

    public void saveVideo(int empId, String fileName, String contentType, byte[] data) {
        employeeVideoDao.saveVideo(empId, fileName, contentType, data);
    }


    public EmployeeVideo getVideo(int empId) {
       return employeeVideoDao.getEmployeeVideo(empId);
    }
}
