package com.example.demo.service;

import com.example.demo.config.JacksonConfig;
import com.example.demo.dao.AssetDaoJdbcImpl;
import com.example.demo.dao.EmployeeDaoImpl;
import com.example.demo.model.*;
import com.example.demo.utils.CsvParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeAssetService {

    private final ObjectMapper mapper;
    private final EmployeeDaoImpl employeeDao;
    private final AssetDaoJdbcImpl assetDao;

    public EmployeeAssetService(ObjectMapper mapper, EmployeeDaoImpl employeeDao, AssetDaoJdbcImpl assetDao) {
        this.mapper = mapper;
        this.employeeDao = employeeDao;
        this.assetDao = assetDao;
    }

    public List<EmployeeWithAssetsDTO> getAllEmployeesWithAssets() {
        mapper.registerModule(new JavaTimeModule());

        List<Employee> employees = employeeDao.findAll();
        List<EmployeeWithAssetsDTO> result = new ArrayList<>();

        for (Employee e : employees) {
            List<AssetsJson> assets = assetDao.findByEmpIdJson(e.getId());

            List<AssetDetail> details = new ArrayList<>();

            if (!assets.isEmpty()) {
                try {
                    details = mapper.readValue(
                            assets.get(0).getAssetDetails(),
                            new TypeReference<List<AssetDetail>>() {
                            }
                    );
                } catch (Exception ex) {
                    throw new RuntimeException("Failed to parse JSON", ex);
                }
            }
            EmployeeWithAssetsDTO dto = new EmployeeWithAssetsDTO();
            dto.setEmployee(e);
            dto.setAssetDetails(details);

            result.add(dto);
        }
        return result;
    }

    public Optional<EmployeeWithAssetsDTO> getEmployeeWithAssets(int empId) {

        Employee e = employeeDao.findById(empId);
        if (e == null) return Optional.empty();

        List<AssetsJson> assets = assetDao.findByEmpIdJson(empId);
        List<AssetDetail> details = new ArrayList<>();

        if (!assets.isEmpty()) {
            try {
                details = mapper.readValue(
                        assets.get(0).getAssetDetails(),
                        new TypeReference<List<AssetDetail>>() {}
                );
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        EmployeeWithAssetsDTO dto = new EmployeeWithAssetsDTO();
        dto.setEmployee(e);
        dto.setAssetDetails(details);

        return Optional.of(dto);
    }


}
