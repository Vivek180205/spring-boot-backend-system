package com.example.demo.service;

import com.example.demo.dao.EmployeeBankDetailsDao;
import com.example.demo.model.EmployeeBankDetails;
import com.example.demo.utils.AESUtil;
import com.example.demo.utils.HashUtil;
import com.example.demo.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionManagement {

    @Autowired
    private EmployeeBankDetailsDao dao;
    @Autowired
    private PasswordUtil passwordUtil;

    public EmployeeBankDetails validateData(String username, String password){

        String hash = HashUtil.sha256(username);
        System.out.println("INPUT USERNAME  : " + username);
        System.out.println("USERNAME HASH   : " + hash);

        EmployeeBankDetails dbData = dao.findByUsernameHash(hash);

        if(dbData == null){
            System.out.println("No user found with this hash");
            return null;
        }

        boolean match = passwordUtil.match(password, dbData.getPassword());
        System.out.println("PASSWORD MATCH : " + match);

        return match ? dbData : null;
    }
}
