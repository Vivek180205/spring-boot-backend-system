package com.example.demo.service;

import com.example.demo.dao.EmployeeDaoImpl;
import com.example.demo.dao.EmployeeResumeDao;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeResume;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EmployeeResumeService {
    private final EmployeeDaoImpl employeeDao;
    private final EmployeeResumeDao employeeResumeDao;

    public EmployeeResumeService(EmployeeDaoImpl employeeDao, EmployeeResumeDao employeeResumeDaoDao) {
        this.employeeDao = employeeDao;
        this.employeeResumeDao = employeeResumeDaoDao;
    }

    public Employee getEmp(int id){
        return employeeDao.findById(id);
    }

    public EmployeeResume getPdf(int id){
        return employeeResumeDao.findEmpById(id);
    }

    public void saveResume(int empId, MultipartFile file) throws IOException {
        byte[] pdfBytes = file.getBytes();
        String fileName = file.getOriginalFilename();

        employeeResumeDao.savePdf(empId, fileName, pdfBytes);
    }


}
