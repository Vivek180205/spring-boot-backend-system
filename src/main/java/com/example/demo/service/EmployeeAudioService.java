package com.example.demo.service;

import com.example.demo.dao.EmployeeAudioDao;
import com.example.demo.model.EmployeeAudio;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EmployeeAudioService {
    private final EmployeeAudioDao employeeAudioDao;

    public EmployeeAudioService(EmployeeAudioDao employeeAudioDao) {
        this.employeeAudioDao = employeeAudioDao;
    }

    public EmployeeAudio getAudioById(int empId){
        return employeeAudioDao.getAudio(empId);
    }

    public void saveAudio(int empId, MultipartFile file) throws IOException {
        employeeAudioDao.saveAudio(empId,file.getOriginalFilename(),file.getContentType(),file.getBytes());
    }
}
