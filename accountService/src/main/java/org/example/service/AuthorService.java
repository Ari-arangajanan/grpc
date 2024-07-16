package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Account;
import org.example.repository.AccountDao;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    private AccountDao authorDao;
    public void insert(Account account) {
        authorDao.insert(account);
    }
}
