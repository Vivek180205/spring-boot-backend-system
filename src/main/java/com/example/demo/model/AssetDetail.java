package com.example.demo.model;

import java.time.LocalDate;

public class AssetDetail {

    private String productNum;
    private String productName;
    private String productType;
    private LocalDate dateOfIssue;
    private Integer totalAssetAllocated;

    public AssetDetail() {}

    public AssetDetail(String productNum, String productName, String productType,
                       LocalDate dateOfIssue, Integer totalAssetAllocated) {
        this.productNum = productNum;
        this.productName = productName;
        this.productType = productType;
        this.dateOfIssue = dateOfIssue;
        this.totalAssetAllocated = totalAssetAllocated;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Integer getTotalAssetAllocated() {
        return totalAssetAllocated;
    }

    public void setTotalAssetAllocated(Integer totalAssetAllocated) {
        this.totalAssetAllocated = totalAssetAllocated;
    }

    @Override
    public String toString() {
        return "AssetDetail{" +
                "productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", totalAssetAllocated=" + totalAssetAllocated +
                '}';
    }
}
