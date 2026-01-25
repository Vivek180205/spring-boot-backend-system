package com.example.demo.model;

import java.util.List;

public class EmployeeWithAssetsDTO {

    private Employee employee;              // employee table ka data
    private List<AssetDetail> assetDetails; // CSV parsed rows

    public EmployeeWithAssetsDTO() {}

    public EmployeeWithAssetsDTO(Employee employee, List<AssetDetail> assetDetails) {
        this.employee = employee;
        this.assetDetails = assetDetails;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<AssetDetail> getAssetDetails() {
        return assetDetails;
    }

    public void setAssetDetails(List<AssetDetail> assetDetails) {
        this.assetDetails = assetDetails;
    }
}
