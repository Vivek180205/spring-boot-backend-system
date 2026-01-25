package com.example.demo.dao;

import com.example.demo.model.EmployeeBankDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeBankDetailsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${bank.query.saveEmployeeBankDetail}")
    private String saveEmployeeBankDetail;

    @Value("${bank.query.findEmployeeBankDetail}")
    private String findEmployeeBankDetail;

    @Value("${bank.query.findByUsernameHash}")
    private String findByUsernameHash;

    @Value("${bank.query.findByUsername}")
    private String findByUsername;

    public EmployeeBankDetails findByUsernameHash(String usernameHash) {
        try {
            return jdbcTemplate.queryForObject(
                    findByUsernameHash,
                    new Object[]{usernameHash},
                    (rs, rowNum) -> {
                        EmployeeBankDetails e = new EmployeeBankDetails();
                        e.setCrn(rs.getLong("crn"));
                        e.setEmpId(rs.getLong("emp_id"));
                        e.setUsernameHash(rs.getString("username_encrypted"));
                        e.setPassword(rs.getString("password_hash"));
                        e.setUsername(rs.getString("username_plain"));

                        return e;
                    }
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public EmployeeBankDetails findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(
                    findByUsername,
                    new Object[]{username},
                    (rs, rowNum) -> {
                        EmployeeBankDetails e = new EmployeeBankDetails();
                        e.setCrn(rs.getLong("crn"));
                        e.setEmpId(rs.getLong("emp_id"));
                        e.setUsername(rs.getString("username_plain"));
                        e.setPassword(rs.getString("password_hash"));

                        return e;
                    }
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(EmployeeBankDetails d) {
            jdbcTemplate.update(saveEmployeeBankDetail,
                    d.getEmpId(),
                    d.getUsernameHash(),
                    d.getPassword(),
                    d.getAccNoHash(),
                    d.getAccNoLast4(),
                    d.getAccNoMasked(),
                    d.getAccType(),
                    d.getUsername()
            );
    }

    public EmployeeBankDetails findByEmpId(Long empId) {

        return jdbcTemplate.queryForObject(findEmployeeBankDetail, new Object[]{empId}, rowMapper());
    }


    // 🔁 ResultSet → Object mapping
    private RowMapper<EmployeeBankDetails> rowMapper() {
        return (rs, rowNum) -> {
            EmployeeBankDetails e = new EmployeeBankDetails();
            e.setCrn(rs.getLong("crn"));
            e.setEmpId(rs.getLong("emp_id"));
            e.setUsernameHash(rs.getString("username_encrypted"));
            e.setPassword(rs.getString("password_hash"));
            e.setAccNoHash(rs.getString("acc_no_hash"));
            e.setAccNoLast4(rs.getString("acc_no_last4"));
            e.setAccNoMasked(rs.getString("acc_no_masked"));
            e.setAccType(rs.getString("acc_type"));
            e.setUsername(rs.getString("username_plain"));

            return e;
        };
    }
}
