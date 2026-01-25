package com.example.demo.dao;

import com.example.demo.model.EmployeeAudio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeAudioDao {
    private final JdbcTemplate jdbc;

    public EmployeeAudioDao(JdbcTemplate jdbc){
        this.jdbc =jdbc;
    }

    @Value("${assets.query.saveAudio}")
    private String saveAudio;
    @Value("${assets.query.getAudio}")
    private String getAudio;

    public EmployeeAudio getAudio(int audioId){
        return jdbc.queryForObject(getAudio, new Object[]{audioId}, (rs , i) ->{
                EmployeeAudio audio = new EmployeeAudio();
                audio.setAudioId(rs.getInt("audio_id"));
                audio.setEmpId(rs.getInt("emp_id"));
                audio.setFileName(rs.getString("file_name"));
                audio.setContentType(rs.getString("content_type"));
                audio.setAudioData(rs.getBytes("audio_data"));
                return audio;
            }
        );
    }

    public int saveAudio(int empId, String fileName, String contentType, byte[] fileData){
        return jdbc.update(saveAudio, empId, fileName, contentType, fileData);
    }


}
