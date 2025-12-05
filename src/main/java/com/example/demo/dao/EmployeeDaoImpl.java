package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.util.List;

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

    @Override
    public List<Employee> findAll() {
//        return jdbc.query(selectAll,(rs,n)-> new Employee(rs.getInt("id"),
//                rs.getString("name"),rs.getString("address"),rs.getString("mobileNo")
//        ,rs.getInt("salary"),rs.getDate("dob"),rs.getBoolean("isActive")));


//          return jdbc.query(
//                        selectAll,
//                        new BeanPropertyRowMapper<>(Employee.class)
//                );

        return jdbc.query(selectAll, new EmployeeRowMapper());
    }

    @Override
    public Employee findById(int id) {
        return jdbc.queryForObject(findById,new Object[]{id},(rs,n)-> new Employee(rs.getInt("id"),
                rs.getString("name"),rs.getString("address"),rs.getString("mobileNo")
                ,rs.getInt("salary"),rs.getDate("dob"),rs.getBoolean("isActive")));

//     return jdbc.queryForObject(
//                findById,
//                new Object[]{id},
//                new BeanPropertyRowMapper<>(Employee.class)
//        );

//        return jdbc.queryForObject(selectAll,new Object[]{id}, new EmployeeRowMapper());

    }

    @Override
    public int updateByValue(String name, String mobileNo) {
        return jdbc.update(updateByValue,name,mobileNo);
    }
}
