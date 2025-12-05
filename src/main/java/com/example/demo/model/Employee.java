package com.example.demo.model;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private String address;
    private String mobileNo;
    private int salary;
    private Date dob;

    public Employee(int id, String name, String address, String mobileNo, int salary, Date dob, boolean isActive) {
        this.id= id;
        this.name= name;
        this.address= address;
        this.mobileNo= mobileNo;
        this.salary= salary;
        this.dob= dob;
        this.isActive= isActive;
    }

    public Employee() {
        //Empty For RowMapper :)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private boolean isActive;
}
