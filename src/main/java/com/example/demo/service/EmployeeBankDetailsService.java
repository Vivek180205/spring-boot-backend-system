package com.example.demo.service;

import com.example.demo.dao.EmployeeBankDetailsDao;
import com.example.demo.model.EmployeeBankDetails;
import com.example.demo.utils.AESUtil;
import com.example.demo.utils.HashUtil;
import com.example.demo.utils.MaskingUtil;
import com.example.demo.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeBankDetailsService {

    @Autowired
    private AESUtil aesUtil;

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private EmployeeBankDetailsDao dao;

    public void save(EmployeeBankDetails req) {
        EmployeeBankDetails secured = prepareData(req);
        dao.save(secured);
    }

    public EmployeeBankDetails getByEmpId(Long empId) {
        EmployeeBankDetails dbData = dao.findByEmpId(empId);
        if (dbData == null) {
            throw new RuntimeException("Bank details not found");
        }
        return dao.findByEmpId(empId);
    }


    public EmployeeBankDetails prepareData(EmployeeBankDetails req) {

        // Hash username and Password
        req.setUsernameHash(HashUtil.sha256(req.getUsername()));
        req.setPassword(passwordUtil.hashPassword(req.getPassword()));

        req.setUsername(req.getUsername());
        String accNo = req.getAccNoPlain();

        req.setAccNoHash(HashUtil.sha256(accNo));
        req.setAccNoLast4(accNo.substring(accNo.length() - 4));
        req.setAccNoMasked(MaskingUtil.maskAccNo(accNo));

        req.setAccNoPlain(null);
        return req;
    }

}
