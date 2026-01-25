package com.example.demo.model;

import lombok.Getter;

@Getter
public class Assets {

    private int assetNo;
    private int empId;
    private String assetDetails; // raw CSV string

    public Assets() {}

    public Assets(int assetNo, int empId, String assetDetails) {
        this.assetNo = assetNo;
        this.empId = empId;
        this.assetDetails = assetDetails;
    }

    public void setAssetNo(int assetNo) {
        this.assetNo = assetNo;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setAssetDetails(String assetDetails) {
        this.assetDetails = assetDetails;
    }
}
