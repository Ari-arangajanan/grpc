package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Book;
import org.example.repository.BookDao;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private BookDao bookDao;
    public void insert(Book book) {
        bookDao.insert(book);
    }
}
