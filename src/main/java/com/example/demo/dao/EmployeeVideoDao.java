package com.example.demo.dao;

import com.example.demo.model.EmployeeVideo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeVideoDao {
    private final JdbcTemplate jdbc;

    public EmployeeVideoDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Value("${assets.query.getVideo}")
    private String getVidById;

    @Value("${assets.query.saveVideo}")
    private String setVidById;

    public EmployeeVideo getEmployeeVideo(int empId){
        return jdbc.queryForObject(getVidById,new Object[]{empId}, (rs,i)->{
                EmployeeVideo emp = new EmployeeVideo();
                emp.setVideoId(rs.getInt("vid_id"));
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setFileName(rs.getString("file_name"));
                emp.setContentType(rs.getString("content_type"));
                emp.setVideoData(rs.getBytes("vid_data"));
                return emp;
            }
        );
    }

    public void saveVideo(int empId, String fileName, String contentType, byte[] vidData){
        jdbc.update(setVidById, empId, fileName, contentType, vidData);
    }
}
