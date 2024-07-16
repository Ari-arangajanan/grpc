package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Account;
import org.example.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountDao authorDao;

    public boolean insert(Account account) {
        try{
            authorDao.insert(account);
            return true;

        }catch (Exception e){
            return false;
        }
    }
}
