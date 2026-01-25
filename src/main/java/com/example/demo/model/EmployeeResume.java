package com.example.demo.model;

public class EmployeeResume {
    private int empId ;
    private String fileName;
    private byte[] resumePDF;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getResumePDF() {
        return resumePDF;
    }

    public void setResumePDF(byte[] resumePDF) {
        this.resumePDF = resumePDF;
    }
}
