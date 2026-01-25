package com.example.demo.dao;

import com.example.demo.model.Assets;
import com.example.demo.model.AssetsJson;
import com.example.demo.model.EmployeeWithAssetsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssetDaoJdbcImpl {

    private final JdbcTemplate jdbc;

    public AssetDaoJdbcImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Value("${assets.query.findByEmpId}")
    private String findByEmpIdQuery;

    @Value("${assets.query.findByEmpIdJson}")
    private String findByEmpIdQueryJson;

    @Value("${assets.query.findAll}")
    private String findAllQuery;

    public List<Assets> findByEmpId(Long empId) {
        return jdbc.query(findByEmpIdQuery, new Object[]{empId}, (rs, rowNum) -> {
            Assets a = new Assets();
            a.setAssetNo(rs.getInt("asset_id"));
            a.setEmpId(rs.getInt("emp_id"));
            a.setAssetDetails(rs.getString("asset_details"));
            return a;
        });
    }

    public List<AssetsJson> findByEmpIdJson(int empId) {
        return jdbc.query(findByEmpIdQueryJson, new Object[]{empId}, (rs, rowNum) -> {
            AssetsJson a = new AssetsJson();
            a.setAssetId(rs.getLong("asset_id"));
            a.setEmpId(rs.getInt("emp_id"));
            a.setAssetDetails(rs.getString("asset_details"));
            return a;
        });
    }


    public List<Assets> findAll() {
        return jdbc.query(findAllQuery, (rs, rowNum) -> {
            Assets a = new Assets();
            a.setAssetNo(rs.getInt("asset_id"));
            a.setEmpId(rs.getInt("emp_id"));
            a.setAssetDetails(rs.getString("asset_details"));
            return a;
        });
    }

}
