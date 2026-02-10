package com.example.demo.model;

public class EmployeeBankDetails {
    private Long crn;
    private Long empId;
    private String accType;
    private String username;
    private String role;


    private String usernameHash;   // Hash
    private String password;   // hashed

    private String accNoHash;
    private String accNoLast4;
    private String accNoMasked;

    private String accNoPlain;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccNoPlain() {
        return accNoPlain;
    }

    public void setAccNoPlain(String accNoPlain) {
        this.accNoPlain = accNoPlain;
    }

    public Long getCrn() {
        return crn;
    }

    public void setCrn(Long crn) {
        this.crn = crn;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccNoHash() {
        return accNoHash;
    }

    public void setAccNoHash(String accNoHash) {
        this.accNoHash = accNoHash;
    }

    public String getAccNoLast4() {
        return accNoLast4;
    }

    public void setAccNoLast4(String accNoLast4) {
        this.accNoLast4 = accNoLast4;
    }

    public String getAccNoMasked() {
        return accNoMasked;
    }

    public void setAccNoMasked(String accNoMasked) {
        this.accNoMasked = accNoMasked;
    }

    public String getUsernameHash() {
        return usernameHash;
    }

    public void setUsernameHash(String usernameHash) {
        this.usernameHash = usernameHash;
    }
}
