package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getInt("id"));
        emp.setName(rs.getString("name"));
        emp.setAddress(rs.getString("address"));
        emp.setMobileNo(rs.getString("mobileNo"));
        emp.setSalary(rs.getInt("salary"));
        emp.setDob(rs.getDate("dob"));
        emp.setActive(rs.getBoolean("isActive"));
        return emp;
    }
}
