package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Author;
import org.example.repository.AuthorDao;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorDao authorDao;
    public void insert(Author author) {
        authorDao.insert(author);
    }
}
