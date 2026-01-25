package com.example.demo.dao;

import com.example.demo.model.EmployeeResume;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class EmployeeResumeDao {
    private final JdbcTemplate jdbc;

    public EmployeeResumeDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Value("${assets.query.findByEmpIdPDF}")
    private String findById;
    @Value("${assets.query.savePDF}")
    private String savePdf;

    public EmployeeResume findEmpById(int id){
        return jdbc.queryForObject(findById, ((rs, i)-> {
            EmployeeResume e = new EmployeeResume();
            e.setEmpId(rs.getInt("emp_id"));
            e.setFileName(rs.getString("file_name"));
            e.setResumePDF(rs.getBytes("resume_pdf"));
            return e;
        }),id);
    }

    public void savePdf(int empId, String fileName, byte[] pdfBytes){
            jdbc.update(savePdf, empId, fileName, pdfBytes);

    }

}

