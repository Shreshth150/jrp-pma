package com.app.projectmanagement.Services;

import com.app.projectmanagement.dao.UserAccountRepository;
import com.app.projectmanagement.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    public void register(UserAccount user){
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        accountRepository.save(user);
    }
}
