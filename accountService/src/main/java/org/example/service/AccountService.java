package org.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.example.model.Account;
import org.example.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService extends ServiceImpl<AccountDao, Account> {

    @Autowired
    private AccountDao authorDao;

//    @Transactional
    public boolean insert(Account account) {
        try{
            authorDao.insert(account);
//            throw new RuntimeException(" -----------------------------");
            return true;

        }catch (Exception e){
            throw new RuntimeException(" -----------------------------");
        }
    }
}
