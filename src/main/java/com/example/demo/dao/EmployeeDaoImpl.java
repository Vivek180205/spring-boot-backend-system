package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbc;
    public EmployeeDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Value("${example.queries.selectAll}")
    private String selectAll;

    @Value("${example.queries.findById}")
    private String findById;

    @Value("${example.queries.updateByValue}")
    private String updateByValue;

    @Value("${example.queries.deleteByValue}")
    private String deleteByValue;

    @Override
    public List<Employee> findAll() {
        // BeanPropertyRowMapper maps column aliases to Java properties (camelCase)
        return jdbc.query(selectAll, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee findById(int id) {
        return jdbc.queryForObject(findById, new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public int updateByValue(Employee e) {
        return jdbc.update(updateByValue,
                e.getName(),
                e.getMobileNo(),
                e.getId()
        );
    }

    @Override
    public int deleteByvalue(int id) {
        return jdbc.update(deleteByValue,id);
    }
}
