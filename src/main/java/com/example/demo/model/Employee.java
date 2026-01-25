package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String mobileNo;
    private Integer salary;
    private Date dob;
    private Boolean active;

    public Employee() {}

    public int getId() {
        return id;
    }

    public Employee(Integer id, String name, String address, String mobileNo, Integer salary, Date dob, Boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
        this.salary = salary;
        this.dob = dob;
        this.active = active;
    }

    // getters & setters
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public Integer getSalary() { return salary; }
    public void setSalary(Integer salary) { this.salary = salary; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
